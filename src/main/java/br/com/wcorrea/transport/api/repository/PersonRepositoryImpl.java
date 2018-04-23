package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Person;
import br.com.wcorrea.transport.api.repository.filter.PersonFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class PersonRepositoryImpl implements PersonRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Encontra todas as Pessoas e retornar o valor de forma paginada
     *
     * @param personFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<Person> findAll(PersonFilter personFilter, Pageable pageable) {
        TypedQuery<Person> queryList = manager.createQuery(this.createQuery(personFilter, false), Person.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(personFilter, true), Long.class);

        UtilsRepository.pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(PersonFilter personFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from person a where 1=1 ";
        } else {
            sql = "from person a where 1=1 ";
        }

        if (StringUtils.isNotBlank(personFilter.getGlobalFilter())) {
            sql += " and (";
//            sql += " upper(a.description) like '%" + personFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.name) like '%" + personFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " )";
        } else {
//            if (StringUtils.isNotBlank(personFilter.getDescription())) {
//                sql += " and upper(a.description) like '%" + personFilter.getDescription().toUpperCase().trim() + "%'";
//            }
            if (StringUtils.isNotBlank(personFilter.getName())) {
                sql += " and upper(a.name) like '%" + personFilter.getName().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(personFilter.getSortField())) {
                sql += " order by a." + personFilter.getSortField();
            }
            if (StringUtils.isNotBlank(personFilter.getSortOrder()) && StringUtils.isNotBlank(personFilter.getSortField())) {
                sql += " " + personFilter.getSortOrder();
            }
        }
        return sql;
    }


}
