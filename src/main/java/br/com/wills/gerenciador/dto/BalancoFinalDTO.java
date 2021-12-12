package br.com.wills.gerenciador.dto;


import java.math.BigDecimal;

public class BalancoFinalDTO {
    private Integer quantidadeEntrada;
    private Integer quantidadeSaida;
    private BigDecimal somaEntradas;
    private BigDecimal somaSaidas;

    public Integer getQuantidadeEntrada() {
        return quantidadeEntrada;
    }

    public void setQuantidadeEntrada(Integer quantidadeEntrada) {
        this.quantidadeEntrada = quantidadeEntrada;
    }

    public Integer getQuantidadeSaida() {
        return quantidadeSaida;
    }

    public void setQuantidadeSaida(Integer quantidadeSaida) {
        this.quantidadeSaida = quantidadeSaida;
    }

    public BigDecimal getSomaEntradas() {
        return somaEntradas;
    }

    public void setSomaEntradas(BigDecimal somaEntradas) {
        this.somaEntradas = somaEntradas;
    }

    public BigDecimal getSomaSaidas() {
        return somaSaidas;
    }

    public void setSomaSaidas(BigDecimal somaSaidas) {
        this.somaSaidas = somaSaidas;
    }
}
