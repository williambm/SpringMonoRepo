package com.wbm.algamoneyapi.algamoneyapi.resource;

import com.wbm.algamoneyapi.algamoneyapi.model.Lancamento;
import com.wbm.algamoneyapi.algamoneyapi.repository.filter.LancamentoFilter;
import com.wbm.algamoneyapi.algamoneyapi.repository.projection.ResumoLancamento;
import com.wbm.algamoneyapi.algamoneyapi.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {

    @Autowired
    private LancamentoService service;

    @GetMapping
    public Page<Lancamento> pesquisar(LancamentoFilter filter, Pageable pageable) {
        return service.listarLancamentos(filter,pageable);
    }

    @GetMapping(params = "resumo")
    //todo: Fluxo com BUG no criteria de Query !!! Posso depois mudar isso para um builder que faz a lógica
    public Page<ResumoLancamento> resumir(LancamentoFilter filter, Pageable pageable) {
        return service.resumirLancamentos(filter,pageable);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity<Lancamento> buscarLancamentoPorCodigo(@PathVariable Long codigo) {
        return ResponseEntity.ok(service.buscarLancamentoPorCodigo(codigo));
    }

    @PostMapping
    public ResponseEntity<Lancamento> cadastrarLancamento(@Valid @RequestBody Lancamento lancamento) {
        lancamento = service.criarLancamento(lancamento);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}").buildAndExpand(lancamento.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(lancamento);
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletarLancamento(@PathVariable Long codigo) {
        service.deletarPeloCodigo(codigo);
    }
}
