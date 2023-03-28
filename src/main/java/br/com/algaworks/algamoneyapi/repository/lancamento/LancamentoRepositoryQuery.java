package br.com.algaworks.algamoneyapi.repository.lancamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public Page<List<Lancamento>> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);
    
}
