package br.com.algaworks.algamoneyapi.controller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import br.com.algaworks.algamoneyapi.exceptionhandler.AlgamoneyExceptionHandler.Erro;
import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.LancamentoRepository;
import br.com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;
import br.com.algaworks.algamoneyapi.service.LancamentoService;
import br.com.algaworks.algamoneyapi.service.exception.PessoaInexistenteOuInativaException;

@RestController
@RequestMapping("lancamentos")
public class LancamentoController {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private LancamentoService lancamentoService;

    @Autowired
    private MessageSource messageSource;

    @GetMapping
    public ResponseEntity pesquisar(LancamentoFilter lancamentoFilter) {

        var lancamentos = lancamentoRepository.filtrar(lancamentoFilter);
        return ResponseEntity.ok(lancamentos);

    }

    @GetMapping("/{codigo}")
    public ResponseEntity lancamentoPorCodigo(@PathVariable Long codigo) {

        var lancamento = lancamentoRepository.getReferenceByCodigo(codigo);
        return ResponseEntity.ok(lancamento);

    }

    @PostMapping
    public ResponseEntity criarLancamento(@Valid @RequestBody Lancamento dados, UriComponentsBuilder uriBuilder) {

        var lancamento = lancamentoService.salvar(dados);
        var uri = uriBuilder.path("/lancamentos/{id}").buildAndExpand(dados.getCodigo()).toUri();

        return ResponseEntity.created(uri).body(lancamento);
        

    }

    @ExceptionHandler({PessoaInexistenteOuInativaException.class})
    public ResponseEntity<Object> handlePessoaInexistenteOuInativaException(PessoaInexistenteOuInativaException ex) {
        String mensagemUsuario = messageSource.getMessage("pessoa.inexistente-ou-inativa", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.toString();
        List<Erro> erros = Arrays.asList(new Erro(mensagemUsuario, mensagemDesenvolvedor));
        return ResponseEntity.badRequest().body(erros);
    }
    
}
