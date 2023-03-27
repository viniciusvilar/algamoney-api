package br.com.algaworks.algamoneyapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.LancamentoRepository;

@RestController
@RequestMapping("lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @GetMapping
    public ResponseEntity listaLancamentos() {

        var lancamentos = lancamentoRepository.findAll();
        return ResponseEntity.ok(lancamentos);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity lancamentoPorCodigo(@PathVariable Long codigo) {

        var lancamento = lancamentoRepository.getReferenceByCodigo(codigo);
        return ResponseEntity.ok(lancamento);

    }

    @PostMapping
    public ResponseEntity criarLancamento(@RequestBody Lancamento dados, UriComponentsBuilder uriBuilder) {

        //System.out.println("AQQQUIIIIIIIIIIIII: "+ dados.getPessoa().isAtivo());
        var lancamento = lancamentoRepository.save(dados);
        var uri = uriBuilder.path("/lancamentos/{id}").buildAndExpand(dados.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(lancamento);
        

    }
    
}
