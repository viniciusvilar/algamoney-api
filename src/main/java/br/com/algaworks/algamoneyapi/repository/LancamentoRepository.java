package br.com.algaworks.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.lancamento.LancamentoRepositoryQuery;

public interface LancamentoRepository extends JpaRepository <Lancamento, Long>, LancamentoRepositoryQuery {

    Lancamento getReferenceByCodigo(Long codigo);
    
}
