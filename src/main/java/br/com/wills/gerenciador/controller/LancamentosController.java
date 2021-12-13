package br.com.wills.gerenciador.controller;

import br.com.wills.gerenciador.dto.BalancoFinalDTO;
import br.com.wills.gerenciador.model.Lancamento;
import br.com.wills.gerenciador.model.RelatorioGastos;
import br.com.wills.gerenciador.service.LancamentoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/lancamentos")
@ApiOperation(value = "Método para verificar lista de Lançamentos.")
public class LancamentosController {

    @Autowired
    LancamentoService lancamentoService;

    @GetMapping("/{id}")
    @ApiOperation(value = "Lista lançamento por Id.")
    public ResponseEntity<Lancamento> buscaLancamentoPorId(@PathVariable Integer id) {
        lancamentoService.buscaLancamentoPorId(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    @ApiOperation(value = "Lista todos lançamentos.")
    public ResponseEntity<List<Lancamento>> todosLancamentos() {
        return ResponseEntity.ok(lancamentoService.buscaLancamentos());
    }

    @PostMapping
    @ApiOperation(value = "Salva lançamento.")
    public ResponseEntity<Lancamento> salvaLancamento(@RequestBody Lancamento lancamento) {
        Lancamento retornoLancamento = lancamentoService.salvaLancamento(lancamento);

        if (retornoLancamento != null) {
            URI uri = UriComponentsBuilder.fromPath("lancamento/{id}").buildAndExpand(retornoLancamento.getId()).toUri();

            return ResponseEntity.created(uri).body(retornoLancamento);
        } else {
           throw new EntityNotFoundException("Limite excedido no limite maximo");
        }
    }

    @PutMapping
    @ApiOperation(value = "Altera lançamento.")
    public ResponseEntity<Lancamento> alteraLancamento(@RequestBody Lancamento lancamento) {
        Lancamento retornoLancamento = lancamentoService.alteraLancamento(lancamento);

        if (retornoLancamento != null) {
            return ResponseEntity.ok(retornoLancamento);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Deleta lançamento por Id.")
    public ResponseEntity<Lancamento> deletaLancamento(@PathVariable Integer id) {
        lancamentoService.deletaLancamento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/relgastos")
    @ApiOperation(value = "Exibe relatorio de gastos por filtro - (D)ia / (M)es / (S)emana / (C)ategoria.")
    public ResponseEntity<List<Lancamento>> relatorioGastos(@RequestParam Integer categoriaId, @RequestParam String filtroData) {
        RelatorioGastos relatorioGastos = new RelatorioGastos();
        relatorioGastos.setCategoriaId(categoriaId);
        relatorioGastos.setFiltroData(filtroData.toUpperCase());
        return ResponseEntity.ok(lancamentoService.relatorioGastos(relatorioGastos));
    }

    @GetMapping("/balanco")
    @ApiOperation(value = "Exibe balanco de entradas e saidas. Inserir uma data váida, porem valida pelo mês")
    public ResponseEntity<BalancoFinalDTO> relatorioBalanco(@RequestParam LocalDate data) {

            return ResponseEntity.ok(lancamentoService.relatorioBalanco(data));
    }
}
