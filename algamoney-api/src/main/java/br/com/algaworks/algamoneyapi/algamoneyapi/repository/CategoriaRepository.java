package br.com.algaworks.algamoneyapi.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.algamoneyapi.algamoneyapi.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Categoria getReferenceByCodigo(Long codigo);
    
}
