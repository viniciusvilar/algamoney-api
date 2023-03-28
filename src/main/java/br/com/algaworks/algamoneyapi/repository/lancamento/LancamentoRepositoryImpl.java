package br.com.algaworks.algamoneyapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.algaworks.algamoneyapi.model.Lancamento;
import br.com.algaworks.algamoneyapi.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @Autowired
    private EntityManager manager;

    @Override
    public List<Lancamento> filtrar(LancamentoFilter lancamentoFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        // criar as retrições
        Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
        criteria.where(predicates);


        TypedQuery<Lancamento> query = manager.createQuery(criteria);
        return null;
    }

    private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();
        
        if (lancamentoFilter.getDescricao() != null) {
            predicates.add(builder.like(
                builder.lower(root.get("descricao")),
                "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"
            ));
        }

        if (lancamentoFilter.getDataVencimentoDe() != null) {
            //predicates.add(e);
        }

        if (lancamentoFilter.getDataVencimentoAte() != null) {
            //predicates.add(e);
        }
        
        return predicates.toArray(new Predicate[predicates.size()]);
    }
    
}