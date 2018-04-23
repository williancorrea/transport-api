package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.TypeRelationship;
import br.com.wcorrea.transport.api.repository.filter.TypeRelationshipFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class TypeRelationshipRepositoryImpl implements TypeRelationshipRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Find all TypeRelationships paginated
     *
     * @param typeRelationshipFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<TypeRelationship> findAll(TypeRelationshipFilter typeRelationshipFilter, Pageable pageable) {
        TypedQuery<TypeRelationship> queryList = manager.createQuery(this.createQuery(typeRelationshipFilter, false), TypeRelationship.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(typeRelationshipFilter, true), Long.class);

        UtilsRepository.pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(TypeRelationshipFilter typeRelationshipFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from type_of_relationship a where 1=1 ";
        } else {
            sql = "from type_of_relationship a where 1=1 ";
        }

        if (StringUtils.isNotBlank(typeRelationshipFilter.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.description) like '%" + typeRelationshipFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.nome) like '%" + typeRelationshipFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.code) like '%" + typeRelationshipFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(typeRelationshipFilter.getDescription())) {
                sql += " and upper(a.description) like '%" + typeRelationshipFilter.getDescription().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(typeRelationshipFilter.getName())) {
                sql += " and upper(a.nome) like '%" + typeRelationshipFilter.getName().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(typeRelationshipFilter.getCode())) {
                sql += " and upper(a.code) like '%" + typeRelationshipFilter.getCode().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(typeRelationshipFilter.getSortField())) {
                sql += " order by a." + typeRelationshipFilter.getSortField();
            }
            if (StringUtils.isNotBlank(typeRelationshipFilter.getSortOrder()) && StringUtils.isNotBlank(typeRelationshipFilter.getSortField())) {
                sql += " " + typeRelationshipFilter.getSortOrder();
            }
        }
        return sql;
    }


}
