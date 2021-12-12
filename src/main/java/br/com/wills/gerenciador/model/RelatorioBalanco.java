package br.com.wills.gerenciador.model;

import java.math.BigDecimal;

public class RelatorioBalanco {
    private Integer quantidade;
    private BigDecimal valorTotal;

    public RelatorioBalanco() {
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }
}
