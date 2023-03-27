package br.com.algaworks.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.algamoneyapi.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Pessoa getReferenceByCodigo(Long codigo);
    void deleteByCodigo(Long codigo);
    
}
