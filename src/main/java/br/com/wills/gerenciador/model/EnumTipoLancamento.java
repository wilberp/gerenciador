package br.com.wills.gerenciador.model;

public enum EnumTipoLancamento {
    D("Despesa"),
    R("Receita");

    private String tipoLancamento;

    EnumTipoLancamento(String tipoLancamento) {
        this.tipoLancamento = tipoLancamento;
    }

    public String getTipoLancamento() {
        return tipoLancamento;
    }

}