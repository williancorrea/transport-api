package br.com.wcorrea.transport.api.repository.banco;

import br.com.wcorrea.transport.api.model.Banco;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BancoRepositoryImpl implements BancoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Busca todos os bancos - paginacao de dados
     *
     * @param filtro
     * @param pageable
     * @return
     */
    @Override
    public Page<Banco> findAll(BancoFiltro filtro, Pageable pageable) {

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
    public int quantidadeRegistrosFiltrados(BancoFiltro filtro) {
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
    private Criteria criarCriteriaParaFiltro(BancoFiltro filtro) {
        Session session = manager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(Banco.class);

        if (StringUtils.isNotBlank(filtro.getFiltroGlobal())) {
            Disjunction disjunction = Restrictions.disjunction(); // Restricao com OR
            disjunction.add(Restrictions.ilike("codigo", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("nome", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            disjunction.add(Restrictions.ilike("url", filtro.getFiltroGlobal(), MatchMode.ANYWHERE));
            criteria.add(disjunction);

            return criteria;
        }

        if (StringUtils.isNotBlank(filtro.getCodigo())) {
            criteria.add(Restrictions.ilike("codigo", filtro.getCodigo(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotBlank(filtro.getNome())) {
            criteria.add(Restrictions.ilike("nome", filtro.getNome(), MatchMode.ANYWHERE));
        }
        if (StringUtils.isNotBlank(filtro.getUrl())) {
            criteria.add(Restrictions.ilike("url", filtro.getUrl(), MatchMode.ANYWHERE));
        }

        return criteria;
    }

//    @Override
//    public Page<Banco> findAll(BancoFiltro bancoFiltro, Pageable pageable) {
//        TypedQuery<Banco> queryList = manager.createQuery(this.createQuery(bancoFiltro, false), Banco.class);
//        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(bancoFiltro, true), Long.class);
//
//        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);
//
//        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
//    }
//
//    private String createQuery(BancoFiltro bancoFiltro, boolean count) {
//        String sql;
//        if (count) {
//            sql = "select count(a) from banco a where 1=1";
//        } else {
//            sql = "from banco a where 1=1 ";
//        }
//
//        if (StringUtils.isNotBlank(bancoFiltro.getFiltroGlobal())) {
//            sql += " and (";
//            sql += " upper(a.codigo) like '%" + bancoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
//            sql += " or upper(a.nome) like '%" + bancoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
//            sql += " or upper(a.url) like '%" + bancoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
//            sql += " )";
//        } else {
//            if (StringUtils.isNotBlank(bancoFiltro.getCodigo())) {
//                sql += " and upper(a.codigo) like '%" + bancoFiltro.getCodigo().toUpperCase().trim() + "%'";
//            }
//            if (StringUtils.isNotBlank(bancoFiltro.getNome())) {
//                sql += " and upper(a.nome) like '%" + bancoFiltro.getNome().toUpperCase().trim() + "%'";
//            }
//            if (StringUtils.isNotBlank(bancoFiltro.getUrl())) {
//                sql += " and upper(a.url) like '%" + bancoFiltro.getUrl().toUpperCase().trim() + "%'";
//            }
//        }
//
//        /**
//         * Ordenacao da lista
//         */
//        if (count == false) {
//            if (StringUtils.isNotBlank(bancoFiltro.getCampoOrdenacao())) {
//                sql += " order by a." + bancoFiltro.getCampoOrdenacao();
//            }
//            if (StringUtils.isNotBlank(bancoFiltro.getOrdemClassificacao()) && StringUtils.isNotBlank(bancoFiltro.getCampoOrdenacao())) {
//                sql += " " + bancoFiltro.getOrdemClassificacao();
//            }
//        }
//        return sql;
//    }
}
