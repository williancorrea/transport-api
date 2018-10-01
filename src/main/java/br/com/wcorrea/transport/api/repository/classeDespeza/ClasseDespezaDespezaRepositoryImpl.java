package br.com.wcorrea.transport.api.repository.classeDespeza;

import br.com.wcorrea.transport.api.model.ClasseDespeza;
import br.com.wcorrea.transport.api.repository.utils.UtilsRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ClasseDespezaDespezaRepositoryImpl implements ClasseDespezaRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Buscar registro de forma paginada
     *
     * @param classeDespezaFiltro
     * @param pageable
     * @return
     */
    @Override
    public Page<ClasseDespeza> findAll(ClasseDespezaFiltro classeDespezaFiltro, Pageable pageable) {
        TypedQuery<ClasseDespeza> queryList = manager.createQuery(this.createQuery(classeDespezaFiltro, false), ClasseDespeza.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(classeDespezaFiltro, true), Long.class);

        UtilsRepository.adicionarRestricoesPaginacao(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(ClasseDespezaFiltro classeDespezaFiltro, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from classe_despeza a where 1=1";
        } else {
            sql = "from classe_despeza a where 1=1 ";
        }

        if (StringUtils.isNotBlank(classeDespezaFiltro.getFiltroGlobal())) {
            sql += " and (";
            sql += " upper(a.descricao) like '%" + classeDespezaFiltro.getFiltroGlobal().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(classeDespezaFiltro.getDescricao())) {
                sql += " and upper(a.descricao) like '%" + classeDespezaFiltro.getDescricao().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(classeDespezaFiltro.getSortField())) {
                sql += " order by a." + classeDespezaFiltro.getSortField();
            }
            if (StringUtils.isNotBlank(classeDespezaFiltro.getSortOrder()) && StringUtils.isNotBlank(classeDespezaFiltro.getSortField())) {
                sql += " " + classeDespezaFiltro.getSortOrder();
            }
        }
        return sql;
    }
}
