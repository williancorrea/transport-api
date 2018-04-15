package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.LevelOfEducation;
import br.com.wcorrea.transport.api.repository.filter.LevelOfEducationFilter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

public class LevelOfEducationRepositoryImpl implements LevelOfEducationRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    /**
     * Find all LevelOfEducations paginated
     *
     * @param levelOfEducationFilter
     * @param pageable
     * @return
     */
    @Override
    public Page<LevelOfEducation> findAll(LevelOfEducationFilter levelOfEducationFilter, Pageable pageable) {
        TypedQuery<LevelOfEducation> queryList = manager.createQuery(this.createQuery(levelOfEducationFilter, false), LevelOfEducation.class);
        TypedQuery<Long> queryTotalRecords = manager.createQuery(this.createQuery(levelOfEducationFilter, true), Long.class);

        UtilsRepository.pagingRestrictions(queryList, pageable);

        return new PageImpl<>(queryList.getResultList(), pageable, queryTotalRecords.getSingleResult());
    }

    private String createQuery(LevelOfEducationFilter levelOfEducationFilter, boolean count) {
        String sql;
        if (count) {
            sql = "select count(a) from level_of_education a where 1=1 ";
        } else {
            sql = "from level_of_education a where 1=1 ";
        }

        if (StringUtils.isNotBlank(levelOfEducationFilter.getGlobalFilter())) {
            sql += " and (";
            sql += " upper(a.description) like '%" + levelOfEducationFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.name) like '%" + levelOfEducationFilter.getGlobalFilter().toUpperCase().trim() + "%'";
            sql += " or upper(a.degreeOfInstructionCaged) = " + levelOfEducationFilter.getGlobalFilter().toUpperCase().trim();
            sql += " or upper(a.degreeOfInstructionSefip) = " + levelOfEducationFilter.getGlobalFilter().toUpperCase().trim();
            sql += " or upper(a.degreeOfInstructionRais) = " + levelOfEducationFilter.getGlobalFilter().toUpperCase().trim();
            sql += " )";
        } else {
            if (StringUtils.isNotBlank(levelOfEducationFilter.getDescription())) {
                sql += " and upper(a.description) like '%" + levelOfEducationFilter.getDescription().toUpperCase().trim() + "%'";
            }
            if (StringUtils.isNotBlank(levelOfEducationFilter.getName())) {
                sql += " and upper(a.name) like '%" + levelOfEducationFilter.getName().toUpperCase().trim() + "%'";
            }
        }

        /**
         * ORDERING THE LIST
         */
        if (count == false) {
            if (StringUtils.isNotBlank(levelOfEducationFilter.getSortField())) {
                sql += " order by a." + levelOfEducationFilter.getSortField();
            }
            if (StringUtils.isNotBlank(levelOfEducationFilter.getSortOrder()) && StringUtils.isNotBlank(levelOfEducationFilter.getSortField())) {
                sql += " " + levelOfEducationFilter.getSortOrder();
            }
        }
        return sql;
    }


}
