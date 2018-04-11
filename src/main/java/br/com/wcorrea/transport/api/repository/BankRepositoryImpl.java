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
    public Page<Bank> findAll(BankFilter bankFilter, Pageable pageable) {
        TypedQuery<Bank> queryList = manager.createQuery(this.createQuery(bankFilter, false), Bank.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(bankFilter, true), Long.class);

        UtilsRepository.pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(BankFilter bankFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from bank a where 1=1";
        } else {
            sql = "from bank a where 1=1 ";
        }

        if (StringUtils.isNotBlank(bankFilter.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.code) like '%" + bankFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.name) like '%" + bankFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.url) like '%" + bankFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(bankFilter.getCode())) {
                sql += " and upper(a.code) like '%" + bankFilter.getCode().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(bankFilter.getName())) {
                sql += " and upper(a.name) like '%" + bankFilter.getName().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(bankFilter.getUrl())) {
                sql += " and upper(a.url) like '%" + bankFilter.getUrl().toUpperCase().trim() + "%'";
            }
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
}
