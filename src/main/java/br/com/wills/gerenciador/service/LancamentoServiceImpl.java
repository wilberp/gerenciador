package br.com.wills.gerenciador.service;

import br.com.wills.gerenciador.dto.BalancoFinalDTO;
import br.com.wills.gerenciador.model.Categoria;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.model.RelatorioGastos;
import br.com.wills.gerenciador.repository.CategoriaRepository;
import br.com.wills.gerenciador.repository.LancamentoRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Service
@Slf4j
public class LancamentoServiceImpl implements LancamentoService {

    private final LancamentoRepository lancamentoRepository;
    private final CategoriaRepository categoriaRepository;

    public LancamentoServiceImpl(LancamentoRepository lancamentoRepository,
                                 CategoriaRepository categoriaRepository) {
        this.lancamentoRepository = lancamentoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Lancamento buscaLancamentoPorId(Integer id) {
        return lancamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lancamento de id " + id + " não encontrado"));
    }

    @Override
    public List<Lancamento> buscaLancamentos() {
        return lancamentoRepository.findAll();
    }

    private BigDecimal verificaCategoriaAtingiuValor(Integer idCategoria) {

        BigDecimal valorRestanteCategoria = BigDecimal.ZERO;
        BigDecimal valorTotalLancamentos = BigDecimal.ZERO;
        LocalDate data = LocalDate.now();
        LocalDate dataInicialMes = data.withDayOfMonth(1);

        Categoria categoria = new Categoria();
        categoria.setId(idCategoria);

        var retCategoria = categoriaRepository.findById(idCategoria);
        var retLancamentos = lancamentoRepository.findBydataCriacaoBetweenAndCategoria(dataInicialMes, data, categoria);

        //o compareTo retorna 3 valores:
        //-1: quando vc compare seu numero com um valor maior
        // 0: quando vc compara seu numero com um numero igual
        // 1: quando vc compara seu numero com um valor menor
    Integer compara = retCategoria.get().getLimiteMensal().compareTo(BigDecimal.ZERO);

        if (compara == 1) {
            for (Lancamento lancamento : retLancamentos) {
                valorTotalLancamentos = lancamento.getValor().add(valorTotalLancamentos);
                valorRestanteCategoria = retCategoria.get().getLimiteMensal().subtract(valorTotalLancamentos);
            }
        }if (compara == 0 || compara == -1) {
                valorTotalLancamentos = new BigDecimal(999999999999.9);
            valorRestanteCategoria = valorTotalLancamentos;
        }

        return valorRestanteCategoria;
    }


    @Override
    public Lancamento salvaLancamento(Lancamento lancamento) {
        if (lancamento != null) {
            var retorno = verificaCategoriaAtingiuValor(lancamento.getCategoria().getId());
            Integer compara = lancamento.getValor().compareTo(retorno);

            if (compara == -1) {
                log.info(String.format("-1:Lançamento Salvo com sucesso: %s", lancamento));
                return lancamentoRepository.save(lancamento);
            }if (compara == 0) {
                    log.info(String.format("0:Lançamento Salvo com sucesso: %s", lancamento));
                    return lancamentoRepository.save(lancamento);
            }  else {
                log.info(String.format("Lançamento Não foi salvo - Motivo limite mensal atingido ou atingir Valor Restante: %s , Valor a inserir: %s", retorno, lancamento.getValor()));
                return null;
            }
        } else {
            log.info(String.format("Lançamento Não foi salvo - Lancamento vazio: %s", lancamento.toString()));
            return null;
        }
    }


    @Override
    public Lancamento alteraLancamento(Lancamento lancamento) {
        Lancamento retornoLancamento = lancamentoRepository.findById(lancamento.getId())
                .orElseThrow(() -> new EntityNotFoundException("Lancamento de id " + lancamento.getId() + " não encontrado"));
        if (retornoLancamento != null) {
            return lancamentoRepository.save(lancamento);
        }
        return lancamento;
    }


    @Override
    public void deletaLancamento(Integer id) {
        Lancamento retornoCategoria = lancamentoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Lancamento de id " + id + " não encontrado"));
        if (retornoCategoria != null) {
            lancamentoRepository.deleteById(retornoCategoria.getId());
        }
    }


    @Override
    public List<Lancamento> relatorioGastos(RelatorioGastos relatorioGastos) {

        List<Lancamento> retorno;

        LocalDate data = LocalDate.now();
        String filtro = relatorioGastos.getFiltroData().toString();

        switch (filtro) {
            case "D":
                log.info(String.format("Tipo %s, Data %s", filtro, data));
                retorno = lancamentoRepository.buscaDia(data);
                break;
            case "M":
                Month mes = LocalDate.now().getMonth();
                log.info(String.format("Tipo %s, Mes %s", filtro, mes));
                retorno = lancamentoRepository.buscaMes(mes.getValue());
                break;
            case "S":
                LocalDate dataAnterior = data.minusDays(7);
                log.info(String.format("Tipo %s, De %s a %s", filtro, dataAnterior, data));
                retorno = lancamentoRepository.buscaSemana(dataAnterior, data);
                break;
            case "C":
                log.info(String.format("Tipo %s, Categoria %s", filtro, relatorioGastos.getCategoriaId()));
                retorno = lancamentoRepository.buscaCategoriaId(relatorioGastos.getCategoriaId());
                break;
            default:
                throw new IllegalArgumentException("Filtro errado, escolher (D)ia/(M)es/(S)emana/(C)ategoria");
        }
        return retorno;
    }

    @Override
    public BalancoFinalDTO relatorioBalanco(LocalDate data) {
        Month mes = data.getMonth();
        log.info(String.format("Iniciando Extração dados para Balanço: Data: %s, Mês referência valor: %s", data, mes));

        BalancoFinalDTO balancoFinalDTO;

        var entrada = lancamentoRepository.buscaBalanco("R", mes.getValue());
        var saida = lancamentoRepository.buscaBalanco("D", mes.getValue());

        balancoFinalDTO = extrairBalanco(entrada, saida);

        return balancoFinalDTO;
    }

    private BalancoFinalDTO extrairBalanco(List<Lancamento> entrada, List<Lancamento> saida) {
        Integer qtdeEntrada = 0;
        Integer qtdesaida = 0;
        BigDecimal valorTotalEntrada = BigDecimal.ZERO;
        BigDecimal valorTotalSaida = BigDecimal.ZERO;

        BalancoFinalDTO balancoFinalDTO = new BalancoFinalDTO();

        for (Lancamento lancamento : entrada) {
            qtdeEntrada++;
            valorTotalEntrada = lancamento.getValor().add(valorTotalEntrada);
        }
        balancoFinalDTO.setQuantidadeEntrada(qtdeEntrada);
        balancoFinalDTO.setSomaEntradas(valorTotalEntrada);
        log.info(String.format("Extração dados para Balanço Entrada - totais: %s, valor: %s", qtdeEntrada, valorTotalEntrada));

        for (Lancamento lancamento : saida) {
            qtdesaida++;
            valorTotalSaida = lancamento.getValor().add(valorTotalSaida);
        }
        balancoFinalDTO.setQuantidadeSaida(qtdesaida);
        balancoFinalDTO.setSomaSaidas(valorTotalSaida);
        log.info(String.format("Extração dados para Balanço Saida - totais: %s, valor: %s", qtdesaida, valorTotalSaida));

        return balancoFinalDTO;
    }


}
