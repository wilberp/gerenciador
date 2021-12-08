package br.com.wills.gerenciador.service;

import br.com.wills.gerenciador.model.Categoria;
import br.com.wills.gerenciador.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria buscaCategoriaPorId(Integer id) {
        return categoriaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Categoria de id " + id + " não encontrado"));
    }

    @Override
    public List<Categoria> buscaCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Categoria salvaCategoria(Categoria categoria) {
        if (categoria!=null){
            return  categoriaRepository.save(categoria);
        }
        return categoria;
    }

    @Override
    public Categoria alteraCategoria(Categoria categoria) {
        Categoria retornoCategoria = categoriaRepository.findById(categoria.getId()).orElseThrow(() -> new EntityNotFoundException("Categoria de id " + categoria.getId() + " não encontrado"));
        if (retornoCategoria != null) {
            return categoriaRepository.save(categoria);
        }
        return categoria;
    }


    @Override
    public void deletaCategoria(Integer id) {
        Categoria retornoCategoria = categoriaRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Categoria de id " + id + " não encontrado"));
        if (retornoCategoria != null) {
            categoriaRepository.deleteById(retornoCategoria.getId());
        }
    }


  }
