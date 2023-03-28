package br.com.algaworks.algamoneyapi.repository.lancamento;

import java.util.List;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {

    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter);
    
}
