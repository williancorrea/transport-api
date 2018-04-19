package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.MaritalStatus;
import br.com.wcorrea.transport.api.repository.filter.MaritalStatusFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class MaritalStatusRepositoryImpl implements MaritalStatusRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Find all MaritalStatuss paginated
     *
     * @param maritalStatusFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<MaritalStatus> findAll(MaritalStatusFilter maritalStatusFilter, Pageable pageable) {
        TypedQuery<MaritalStatus> queryList = manager.createQuery(this.createQuery(maritalStatusFilter, false), MaritalStatus.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(maritalStatusFilter, true), Long.class);

        UtilsRepository.pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(MaritalStatusFilter maritalStatusFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from marital_status a where 1=1 ";
        } else {
            sql = "from marital_status a where 1=1 ";
        }

        if (StringUtils.isNotBlank(maritalStatusFilter.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.description) like '%" + maritalStatusFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.name) like '%" + maritalStatusFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(maritalStatusFilter.getDescription())) {
                sql += " and upper(a.description) like '%" + maritalStatusFilter.getDescription().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(maritalStatusFilter.getName())) {
                sql += " and upper(a.name) like '%" + maritalStatusFilter.getName().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(maritalStatusFilter.getSortField())) {
                sql += " order by a." + maritalStatusFilter.getSortField();
            }
            if (StringUtils.isNotBlank(maritalStatusFilter.getSortOrder()) && StringUtils.isNotBlank(maritalStatusFilter.getSortField())) {
                sql += " " + maritalStatusFilter.getSortOrder();
            }
        }
        return sql;
    }


}
