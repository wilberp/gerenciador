package br.com.wills.gerenciador.service;

import br.com.wills.gerenciador.model.Categoria;

import java.util.List;

public interface CategoriaService {

    Categoria buscaCategoriaPorId(Integer id);
    List<Categoria> buscaCategorias();
    Categoria salvaCategoria(Categoria categoria);
    Categoria alteraCategoria(Categoria categoria);
    void deletaCategoria (Integer id);

}
