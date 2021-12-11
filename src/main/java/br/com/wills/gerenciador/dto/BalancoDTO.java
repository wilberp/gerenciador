package br.com.wills.gerenciador.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BalancoDTO {
    private Integer quantidadeEntrada;
    private Integer quantidadeSaida;
    private BigDecimal somaEntradas;
    private BigDecimal somaSaidas;
    private LocalDate data;
}
