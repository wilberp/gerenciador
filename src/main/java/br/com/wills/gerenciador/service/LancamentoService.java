package br.com.wills.gerenciador.service;

import br.com.wills.gerenciador.dto.BalancoDTO;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.model.RelatorioGastos;

import java.time.LocalDate;
import java.util.List;

public interface LancamentoService {
    Lancamento buscaLancamentoPorId(Integer id);
    List<Lancamento> buscaLancamentos();
    Lancamento salvaLancamento(Lancamento lancamento);
    Lancamento alteraLancamento(Lancamento lancamento);
    void deletaLancamento (Integer id);
    List<Lancamento> relatorioGastos(RelatorioGastos relatorioGastos);
    BalancoDTO relatorioBalanco(LocalDate data);

    }
