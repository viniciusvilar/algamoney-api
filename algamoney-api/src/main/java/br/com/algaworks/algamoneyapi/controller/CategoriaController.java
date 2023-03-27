package br.com.algaworks.algamoneyapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algaworks.algamoneyapi.model.Categoria;
import br.com.algaworks.algamoneyapi.repository.CategoriaRepository;

@RestController
@RequestMapping("categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public ResponseEntity<List<Categoria>> listar() {
        var categoria = categoriaRepository.findAll();
        return ResponseEntity.ok(categoria);
    }

    @PostMapping
    public ResponseEntity criar(@Valid @RequestBody Categoria dados, UriComponentsBuilder uriBuilder) {
        var categoria = new Categoria(dados.getNome());
        categoriaRepository.save(categoria);

        var uri = uriBuilder.path("/categorias/{id}").buildAndExpand(categoria.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(categoria);
    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {
        var categoria = categoriaRepository.getReferenceByCodigo(codigo);

        if (categoria != null) {
            return ResponseEntity.ok(categoria);
        } 

        return ResponseEntity.notFound().build();
        
    }

    
}
