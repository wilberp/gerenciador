package br.com.wills.gerenciador.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class RelatorioGastos {
    private Integer categoriaId;
    @Enumerated(EnumType.STRING)
    private EnumRelatorioFiltro filtroData;

    public Integer getCategoriaId() {
        return categoriaId;
    }

    public EnumRelatorioFiltro getFiltroData() {
        return filtroData;
    }

    public void setCategoriaId(Integer categoriaId) {this.categoriaId = categoriaId; }

    public void setFiltroData(EnumRelatorioFiltro filtroData) {this.filtroData = filtroData; }
}
