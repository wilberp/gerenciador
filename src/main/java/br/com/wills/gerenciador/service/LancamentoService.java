package br.com.wills.gerenciador.service;

import br.com.wills.gerenciador.model.Lancamento;

import java.util.List;

public interface LancamentoService {
    Lancamento buscaLancamentoPorId(Integer id);
    List<Lancamento> buscaLancamentos();
    Lancamento salvaLancamento(Lancamento lancamento);
    Lancamento alteraLancamento(Lancamento lancamento);
    void deletaLancamento (Integer id);

    }
