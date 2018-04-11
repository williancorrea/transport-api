package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.ProductUnit;
import br.com.wcorrea.transport.api.repository.filter.ProductUnitFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class ProductUnitRepositoryImpl implements ProductUnitRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Find all ProductUnits paginated
     *
     * @param productUnitFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<ProductUnit> findAll(ProductUnitFilter productUnitFilter, Pageable pageable) {
        TypedQuery<ProductUnit> queryList = manager.createQuery(this.createQuery(productUnitFilter, false), ProductUnit.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(productUnitFilter, true), Long.class);

        UtilsRepository.pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(ProductUnitFilter ProductUnitFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from product_unit a where 1=1 ";
        } else {
            sql = "from product_unit a where 1=1 ";
        }

        if (StringUtils.isNotBlank(ProductUnitFilter.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.initials) like '%" + ProductUnitFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.name) like '%" + ProductUnitFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(ProductUnitFilter.getInitials())) {
                sql += " and upper(a.initials) like '%" + ProductUnitFilter.getInitials().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(ProductUnitFilter.getName())) {
                sql += " and upper(a.name) like '%" + ProductUnitFilter.getName().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(ProductUnitFilter.getSortField())) {
                sql += " order by a." + ProductUnitFilter.getSortField();
            }
            if (StringUtils.isNotBlank(ProductUnitFilter.getSortOrder()) && StringUtils.isNotBlank(ProductUnitFilter.getSortField())) {
                sql += " " + ProductUnitFilter.getSortOrder();
            }
        }
        return sql;
    }


}
