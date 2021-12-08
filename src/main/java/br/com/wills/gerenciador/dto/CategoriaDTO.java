package br.com.wills.gerenciador.dto;

import java.math.BigDecimal;

public class CategoriaDTO {

    private String nome;
    private BigDecimal limiteMensal;

    public CategoriaDTO(String nome, BigDecimal limiteMensal)
    {
        this.nome = nome;
        this.limiteMensal = limiteMensal;
    }

    protected CategoriaDTO() {
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getLimiteMensal() { return limiteMensal; }
}