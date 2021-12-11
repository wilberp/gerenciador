package br.com.wills.gerenciador.model;

public class RelatorioGastos {
    private Integer categoriaId;
    private String filtroData;

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public String getFiltroData() {
        return filtroData;
    }

    public void setCategoriaId(Integer categoriaId) {this.categoriaId = categoriaId; }

    public void setFiltroData(String filtroData) {this.filtroData = filtroData; }
}
