package br.com.wills.gerenciador.model;

public enum EnumRelatorioFiltro {
    D("Data"),
    M("Mes"),
    S("Semana"),
    C("Categoria");

    private String filtro;

    EnumRelatorioFiltro(String filtro) {
        this.filtro = filtro;
    }

    public String getFiltro() {
        return filtro;
    }
}