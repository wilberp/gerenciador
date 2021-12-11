package br.com.wills.gerenciador.service;

import br.com.wills.gerenciador.dto.BalancoDTO;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.model.RelatorioGastos;
import br.com.wills.gerenciador.repository.LancamentoRepository;
import br.com.wills.gerenciador.repository.LancamentoSpecification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private final LancamentoRepository lancamentoRepository;

    public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
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

    private boolean verificaCategoriaLazer200() {
        LocalDate data = LocalDate.now();
        List<Lancamento> retorno = lancamentoRepository.findAll(where(LancamentoSpecification.categoriaLazer())
                .and(LancamentoSpecification.dataMes(data)));

        return retorno.size() == 0 ? false : true;
    }

    @Override
    public Lancamento salvaLancamento(Lancamento lancamento) {
        if (lancamento != null) {
            boolean retorno = verificaCategoriaLazer200();
            return retorno == false ? lancamentoRepository.save(lancamento) : null;
        } else {
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
        String filtro = relatorioGastos.getFiltroData();

        switch (filtro) {
            case "D":
                System.out.println(String.format("Tipo %s, Data %s", filtro, data));
                retorno = lancamentoRepository.buscaDia(data);
                break;
            case "M":
                Month mes = LocalDate.now().getMonth();
                System.out.println(String.format("Tipo %s, Mes %s", filtro, mes));
                retorno = lancamentoRepository.buscaMes(mes.getValue());
                break;
            case "S":
                LocalDate dataAnterior = data.plusDays(-7);
                System.out.println(String.format("Tipo %s, De %s a %s", filtro, dataAnterior, data));
                retorno = lancamentoRepository.buscaSemana(dataAnterior, data);
                break;
            case "C":
                System.out.println(String.format("Tipo %s, Categoria %s", filtro, relatorioGastos.getCategoriaId()));
                retorno = lancamentoRepository.buscaCategoriaId(relatorioGastos.getCategoriaId());
                break;
            default:
                throw new IllegalArgumentException("Filtro errado, escolher (D)ia/(M)es/(S)emana/(C)ategoria");
        }
        return retorno;
    }

    @Override
    public BalancoDTO relatorioBalanco(LocalDate data) {
        Month mes = data.getMonth();
        Object objeto = lancamentoRepository.buscaBalancoSaida(mes.getValue());
        return null;??
    }
}
