package br.com.wcorrea.transport.api.repository.veiculo.combustivel;

import br.com.wcorrea.transport.api.model.Combustivel;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class CombustivelRepositoryImpl implements CombustivelRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Busca todos os Combustivel - paginacao de dados
     *
     * @param filtro
     * @param pageable
     * @return
     */
    @Override
    public Page<Combustivel> findAll(CombustivelFiltro filtro, Pageable pageable) {

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
    public int quantidadeRegistrosFiltrados(CombustivelFiltro filtro) {
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
    private Criteria criarCriteriaParaFiltro(CombustivelFiltro filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Combustivel.class);

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            Disjunction disjunction = Restrictions.disjunction(); // Restricao com OR
            disjunction.add(Restrictions.ilike("nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            criteria.add(disjunction);

            return criteria;
        }

        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }

        if (filtro.isSomenteAtivo()) {
            criteria.add(Restrictions.eq("inativo", false));
        }
        return criteria;
    }
}
