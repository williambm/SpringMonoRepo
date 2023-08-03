package com.wbm.algamoneyapi.algamoneyapi.repository.lancamento;

import com.wbm.algamoneyapi.algamoneyapi.model.Lancamento;
import com.wbm.algamoneyapi.algamoneyapi.repository.filter.LancamentoFilter;
import com.wbm.algamoneyapi.algamoneyapi.repository.projection.ResumoLancamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Lancamento> filtrar(LancamentoFilter filter, Pageable pageable) {
        CriteriaBuilder criteriaBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Lancamento> criteriaQuery = criteriaBuilder.createQuery(Lancamento.class);
        Root<Lancamento> root = criteriaQuery.from(Lancamento.class);

        //Criar restrições
        Predicate[] predicates = criarRestricoes(filter, criteriaBuilder, root);
        criteriaQuery.where(predicates);

        TypedQuery<Lancamento> query = manager.createQuery(criteriaQuery);
        adicionarRestricaoDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }

    @Override
    public Page<ResumoLancamento> resumoLancamento(LancamentoFilter filter, Pageable pageable) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        criteria.select(builder.construct(ResumoLancamento.class,
                root.get("codigo"), root.get("descricao"),
                root.get("dataVencimento"), root.get("dataPagamento"),
                root.get("valor"), root.get("tipo"),
                root.get("categoria").get("nome"),
                root.get("pessoa").get("nome")));

        //Criar restrições
        Predicate[] predicates = criarRestricoes(filter, builder, root);
        criteria.where(predicates);

        TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
        adicionarRestricaoDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filter));
    }


    private Predicate[] criarRestricoes(LancamentoFilter filter, CriteriaBuilder criteriaBuilder
            , Root<Lancamento> root) {

        List<Predicate> predicates = new ArrayList<>();

        if (!ObjectUtils.isEmpty(filter.getDescricao())) {
            predicates.add(criteriaBuilder.like(
                    criteriaBuilder.lower(root.get("descricao")), "%" + filter.getDescricao().toLowerCase() + "%"
            ));
        }
        if (filter.getDataVencimentoDe() != null) {
            predicates.add(
                    criteriaBuilder.greaterThanOrEqualTo(
                            root.get("dataVencimento"), filter.getDataVencimentoDe()
                    )
            );

        }
        if (filter.getDataVencimentoAte() != null) {
            predicates.add((
                    criteriaBuilder.lessThanOrEqualTo(
                            root.get("dataVencimento"), filter.getDataVencimentoAte()
                    )
            ));
        }

        return predicates.toArray(new Predicate[predicates.size()]);
    }


    private void adicionarRestricaoDePaginacao(TypedQuery<?> query, Pageable pageable) {
        int paginaAtual = pageable.getPageNumber();
        int totalRegistrosPorPagina = pageable.getPageSize();
        int primeiroRegistroDaPagina = paginaAtual * totalRegistrosPorPagina;

        query.setFirstResult(primeiroRegistroDaPagina);
        query.setMaxResults(totalRegistrosPorPagina);
    }

    private Long total(LancamentoFilter filter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Lancamento> root = criteria.from(Lancamento.class);

        Predicate[] predicates = criarRestricoes(filter, builder, root);

        criteria.where(predicates);

        //Faz um count para pegar todos os registros da tabela
        criteria.select(builder.count(root));

        return manager.createQuery(criteria).getSingleResult();
    }
}
