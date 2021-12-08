package br.com.wills.gerenciador.controller;

import br.com.wills.gerenciador.dto.CategoriaDTO;
import br.com.wills.gerenciador.dto.LancamentoDTO;
import br.com.wills.gerenciador.model.Categoria;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.repository.LancamentoRepository;
import br.com.wills.gerenciador.service.LancamentoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@ApiOperation(value = "Método para verificar lista de Lançamentos.")
public class LancamentosController {

	@Autowired
	LancamentoService lancamentoService;

	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> buscaLancamentoPorId(@PathVariable Integer id)  {
		lancamentoService.buscaLancamentoPorId(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Lancamento>> todosLancamentos(){
		return ResponseEntity.ok(lancamentoService.buscaLancamentos());
	}

	@PostMapping
	public ResponseEntity<Lancamento> salvaLancamento(@RequestBody Lancamento lancamento)  {
		Lancamento retornoLancamento = lancamentoService.salvaLancamento(lancamento);

		if (retornoLancamento != null){
			URI uri = UriComponentsBuilder.fromPath("lancamento/{id}").buildAndExpand(retornoLancamento.getId()).toUri();

			return ResponseEntity.created(uri).body(retornoLancamento);
		}else{
			return ResponseEntity.noContent().build();
		}
	}

	@PutMapping
	public ResponseEntity<Lancamento> alteraLancamento(@RequestBody Lancamento lancamento)  {
		Lancamento retornoLancamento = lancamentoService.alteraLancamento(lancamento);

		if (retornoLancamento != null){
			return ResponseEntity.ok(retornoLancamento);
		}else{
			return ResponseEntity.noContent().build();
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Lancamento> deletaLancamento(@PathVariable Integer id)  {
		lancamentoService.deletaLancamento(id);
		return ResponseEntity.noContent().build();
	}
}
