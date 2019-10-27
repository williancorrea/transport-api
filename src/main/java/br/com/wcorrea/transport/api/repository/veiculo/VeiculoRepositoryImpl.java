package br.com.wcorrea.transport.api.repository.veiculo;

import br.com.wcorrea.transport.api.model.veiculo.Veiculo;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.hibernate.sql.JoinType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class VeiculoRepositoryImpl implements VeiculoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * CARREGA TODOS OS REGISTROS DE FORMA PAGINADA
     *
     * @param filtro
     * @param paginacao
     * @return
     */
    @Override
    public Page<Veiculo> findAll(VeiculoFiltro filtro, Pageable paginacao) {
        //Criterios da pesquisa
        Criteria criteria = criarCriteriaParaFiltro(filtro);

        //Paginacao
        criteria.setFirstResult(paginacao.getPageNumber() * paginacao.getPageSize());
        criteria.setMaxResults(paginacao.getPageSize());

        //Ordenacao
        if (filtro.getCampoOrdenacao() != null && filtro.getOrdemClassificacao() != null) {
            if (filtro.getOrdemClassificacao().equalsIgnoreCase("ASC")) {
                criteria.addOrder(Order.asc(filtro.getCampoOrdenacao()));
            } else {
                criteria.addOrder(Order.desc(filtro.getCampoOrdenacao()));
            }
        }
        //Consulta paginada
        return new PageImpl<>(criteria.list(), paginacao, quantidadeRegistrosFiltrados(filtro));
    }


    /**
     * RECUPERA A QUANTIDADE DE REGISTRO
     *
     * @param filtro
     * @return
     */
    public int quantidadeRegistrosFiltrados(VeiculoFiltro filtro) {
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
    private Criteria criarCriteriaParaFiltro(VeiculoFiltro filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Veiculo.class);
        criteria.createAlias("combustivel", "c", JoinType.LEFT_OUTER_JOIN);

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            Disjunction disjunction = Restrictions.disjunction(); // Restricao com OR
            disjunction.add(Restrictions.ilike("frota", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("placa", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));

            disjunction.add(Restrictions.sqlRestriction(" concat(lower({alias}.frota), ' - ', lower({alias}.placa)) like '%" + filtro.getFiltroGlobal() + "%' "));
            disjunction.add(Restrictions.sqlRestriction(" concat(lower({alias}.placa), ' - ', lower({alias}.frota)) like '%" + filtro.getFiltroGlobal() + "%' "));
            criteria.add(disjunction);
        }

        //SOMENTE SOMENTE VEICULOS AUTORIZADO A FAZER FRETAMENTO
        if (filtro.isSomenteVeiculosQuePodemSerFretados()) {
            criteria.add(Restrictions.eq("podeSerFretado", true));
        }

        //SOMENTE CADASTROS ATIVOS
        if (filtro.isSomenteAtivo()) {
            criteria.add(Restrictions.eq("inativo", false));
        }

        return criteria;
    }

}
