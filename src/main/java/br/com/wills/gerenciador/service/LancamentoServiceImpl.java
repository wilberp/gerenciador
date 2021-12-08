package br.com.wills.gerenciador.service;

import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.repository.LancamentoRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

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

    @Override
    public Lancamento salvaLancamento(Lancamento lancamento) {
        if (lancamento!=null){
            return  lancamentoRepository.save(lancamento);
        }
        return lancamento;
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
}
