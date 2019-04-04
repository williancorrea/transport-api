package br.com.wcorrea.transport.api.repository.tanqueCombustivel;

import br.com.wcorrea.transport.api.model.TanqueCombustivel;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class TanqueCombustivelRepositoryImpl implements TanqueCombustivelRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Busca todos os TanqueCombustivel - paginacao de dados
     *
     * @param filtro
     * @param pageable
     * @return
     */
    @Override
    public Page<TanqueCombustivel> findAll(TanqueCombustivelFiltro filtro, Pageable pageable) {

        //Criterios da pesquisa
        Criteria criteria = criarCriteriaParaFiltro(filtro);

        //Paginacao
        criteria.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        criteria.setMaxResults(pageable.getPageSize());

        //Ordenacao
        if (filtro.getCampoOrdenacao() != null && filtro.getOrdemClassificacao() != null) {
            if (filtro.getOrdemClassificacao().equalsIgnoreCase("ASC")) {
                criteria.addOrder(Order.asc(filtro.getCampoOrdenacao()));
            } else {
                criteria.addOrder(Order.desc(filtro.getCampoOrdenacao()));
            }
        }

        //Consulta paginada
        return new PageImpl<>(criteria.list(), pageable, quantidadeRegistrosFiltrados(filtro));
    }

    /**
     * RECUPERA A QUANTIDADE DE REGISTRO
     *
     * @param filtro
     * @return
     */
    public int quantidadeRegistrosFiltrados(TanqueCombustivelFiltro filtro) {
        Criteria criteria = criarCriteriaParaFiltro(filtro);
        criteria.setProjection(Projections.rowCount());
        return ((Number) criteria.uniqueResult()).intValue();
    }

    /**
     * MONTA OS CRITERIOS PARA A PESQUISA
     *
     * @param filtro
     * @return
     */
    private Criteria criarCriteriaParaFiltro(TanqueCombustivelFiltro filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(TanqueCombustivel.class);

        criteria.createAlias("combustivel", "c");

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            Disjunction disjunction = Restrictions.disjunction(); // Restricao com OR
            disjunction.add(Restrictions.ilike("nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("c.nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            criteria.add(disjunction);

            return criteria;
        }

//        if (StringUtils.isNotBlank(filtro.getNome())) {
//            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
//        }

        return criteria;
    }
}
