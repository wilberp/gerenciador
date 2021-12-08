package br.com.wills.gerenciador.dto;

import br.com.wills.gerenciador.model.Categoria;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoDTO {

    private String tipoLancamento;
    private String descricao;
    private BigDecimal valor = BigDecimal.ZERO;
    private LocalDate dataCriacao = LocalDate.now();
    private Categoria categoria;

    public LancamentoDTO(String tipoLancamento, String descricao, BigDecimal valor, LocalDate dataCriacao, Categoria categoria) {
        super();
        this.tipoLancamento = tipoLancamento;
        this.descricao = descricao;
        this.valor = valor;
        this.dataCriacao = dataCriacao;
        this.categoria = categoria;
    }

    public LancamentoDTO() {
    }

    public String getTipoLancamento() {
        return tipoLancamento;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public Categoria getCategoria() {
        return categoria;
    }
}
