package br.com.wcorrea.transport.api.repository.CentroDeCusto;

import br.com.wcorrea.transport.api.model.CentroDeCusto;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class CentroDeCustoRepositoryImpl implements CentroDeCustoRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Buscar registro de forma paginada
     *
     * @param centroDeCustoFiltro
     * @param pageable
     * @return
     */
    @Override
    public Page<CentroDeCusto> findAll(CentroDeCustoFiltro centroDeCustoFiltro, Pageable pageable) {
        TypedQuery<CentroDeCusto> queryList = manager.createQuery(this.createQuery(centroDeCustoFiltro, false), CentroDeCusto.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(centroDeCustoFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(CentroDeCustoFiltro centroDeCustoFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from CentroDeCusto a where 1=1";
        } else {
            sql = "from CentroDeCusto a where 1=1 ";
        }

        if (StringUtils.isNotBlank(centroDeCustoFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + centroDeCustoFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            if (centroDeCustoFiltro.getClasseId() != null) {
                sql += " or 1=1 and a.classeDespeza.id = " + centroDeCustoFiltro.getClasseId();
            }
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(centroDeCustoFiltro.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + centroDeCustoFiltro.getDescricao().toUpperCase().trim() + "%'";
            }
            if (centroDeCustoFiltro.getClasseId() != null) {
                sql += " and a.classeDespeza.id = " + centroDeCustoFiltro.getClasseId();
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(centroDeCustoFiltro.getSortField())) {
                sql += " order by a." + centroDeCustoFiltro.getSortField();
            }
            if (StringUtils.isNotBlank(centroDeCustoFiltro.getSortOrder()) && StringUtils.isNotBlank(centroDeCustoFiltro.getSortField())) {
                sql += " " + centroDeCustoFiltro.getSortOrder();
            }
        }
        return sql;
    }
}
