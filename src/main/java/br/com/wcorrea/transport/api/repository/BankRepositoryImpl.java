package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Bank;
import br.com.wcorrea.transport.api.repository.filter.BankFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class BankRepositoryImpl implements BankRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Find all banks paginated
     *
     * @param bankFilter
     * @param pageable
     * @return
     */
    @Override
//    public Page<Bank> findAll(BankFilter bankFilter, Pageable pageable) {
//        CriteriaBuilder builder = manager.getCriteriaBuilder();
//        CriteriaQuery<Bank> criteria = builder.createQuery(Bank.class);
//        Root<Bank> root = criteria.from(Bank.class);
//
//        Predicate[] predicates = createRestrictions(bankFilter, builder, root);
//        criteria.where(predicates);
//
//        //CONSTRUIR COM NATIVE QUERY
//        TypedQuery<Bank> query = manager.createQuery(criteria);
//
//        pagingRestrictions(query, pageable);
//
//        return new PageImpl<>(query.getResultList(), pageable, totalRecords(bankFilter));
//    }

    public Page<Bank> findAll(BankFilter bankFilter, Pageable pageable) {
        TypedQuery<Bank> queryList = manager.createQuery(this.createQuery(bankFilter, false), Bank.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(bankFilter, true), Long.class);

        pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(BankFilter bankFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from bank a where 1=1";
        } else {
            sql = "from bank a where 1=1 ";
        }

        if (StringUtils.isNoneBlank(bankFilter.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.code) like '%" + bankFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.name) like '%" + bankFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.url) like '%" + bankFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(bankFilter.getSortField())) {
                sql += " order by a." + bankFilter.getSortField();
            }
            if (StringUtils.isNotBlank(bankFilter.getSortOrder()) && StringUtils.isNotBlank(bankFilter.getSortField())) {
                sql += " " + bankFilter.getSortOrder();
            }
        }
        return sql;
    }

    /**
     * Adds restrictions to data paging
     *
     * @param query
     * @param pageable
     */
    private void pagingRestrictions(TypedQuery<?> query, Pageable pageable) {
        query.setFirstResult(pageable.getPageNumber());
        query.setMaxResults(pageable.getPageSize());
    }
}
