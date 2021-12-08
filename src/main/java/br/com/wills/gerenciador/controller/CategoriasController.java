package br.com.wills.gerenciador.controller;

import br.com.wills.gerenciador.dto.CategoriaDTO;
import br.com.wills.gerenciador.model.Categoria;
import br.com.wills.gerenciador.service.CategoriaService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@ApiOperation(value = "Método para verificar lista de categorias.")
public class CategoriasController {

    private final CategoriaService categoriaService;

    @Autowired
    public CategoriasController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscaCategoriaPorId(@PathVariable Integer id)  {
        Categoria retornoCategoria = categoriaService.buscaCategoriaPorId(id);
        if (retornoCategoria != null){
            return ResponseEntity.ok(retornoCategoria);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<Categoria>> todasCategorias(){
        return ResponseEntity.ok(categoriaService.buscaCategorias());
    }

    @PostMapping
    public ResponseEntity<Categoria> salvaCategoria(@RequestBody Categoria categoria)  {
        Categoria retornoCategoria = categoriaService.salvaCategoria(categoria);

        if (retornoCategoria != null){
            URI uri = UriComponentsBuilder.fromPath("categoria/{id}").buildAndExpand(retornoCategoria.getId()).toUri();

            return ResponseEntity.created(uri).body(retornoCategoria);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @PutMapping
    public ResponseEntity<Categoria> alteraCategoria(@RequestBody Categoria categoria)  {
        Categoria retContato = categoriaService.alteraCategoria(categoria);

        if (retContato != null){
            return ResponseEntity.ok(retContato);
        }else{
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CategoriaDTO> deletaCategoria(@PathVariable Integer id)  {
        categoriaService.deletaCategoria(id);
        return ResponseEntity.noContent().build();
    }
}
