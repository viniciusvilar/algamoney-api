package br.com.algaworks.algamoneyapi.algamoneyapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.algaworks.algamoneyapi.algamoneyapi.model.Pessoa;
import br.com.algaworks.algamoneyapi.algamoneyapi.repository.PessoaRepository;

@RestController
@RequestMapping("pessoas")
public class PessoaController {

    @Autowired
    private PessoaRepository pessoaRepository;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listar() {
        var pessoas = pessoaRepository.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @PostMapping
    @Transactional
    public ResponseEntity criar(@Valid @RequestBody Pessoa dados, UriComponentsBuilder uriBuilder) {

        var pessoa = new Pessoa(dados.getNome(), dados.getEndereco(), dados.isAtivo());
        pessoaRepository.save(pessoa);

        var uri = uriBuilder.path("/pessoas/{id}").buildAndExpand(pessoa.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(pessoa);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity buscarPeloCodigo(@PathVariable Long codigo) {

        var pessoa = pessoaRepository.getReferenceByCodigo(codigo);

        return pessoa != null ? ResponseEntity.ok(pessoa) : ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity deletar(@PathVariable Long codigo) {

        var pessoa = pessoaRepository.getReferenceByCodigo(codigo);
        if (pessoa != null) {
            pessoaRepository.delete(pessoa);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{codigo}")
    public ResponseEntity<Pessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody Pessoa pessoa) {
        var pessoaSalva = pessoaRepository.getReferenceByCodigo(codigo);
        if (pessoaSalva == null) {
            return ResponseEntity.notFound().build();
        }
        BeanUtils.copyProperties(pessoa, pessoaSalva, "codigo");
        pessoaRepository.save(pessoaSalva);
        return ResponseEntity.ok(pessoaSalva);
    }

    @PutMapping("/{codigo}/ativo")
    public ResponseEntity atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
        var pessoaSalva = pessoaRepository.getReferenceByCodigo(codigo);
        if (pessoaSalva == null) {
            return ResponseEntity.notFound().build();
        }
        pessoaSalva.setAtivo(ativo);
        pessoaRepository.save(pessoaSalva);
        return ResponseEntity.ok(pessoaSalva);
    }
}
