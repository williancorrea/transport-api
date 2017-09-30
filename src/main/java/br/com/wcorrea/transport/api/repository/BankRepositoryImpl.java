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
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Bank> criteria = builder.createQuery(Bank.class);
        Root<Bank> root = criteria.from(Bank.class);

        Predicate[] predicates = createRestrictions(bankFilter, builder, root);
        criteria.where(predicates);

        TypedQuery<Bank> query = manager.createQuery(criteria);
        pagingRestrictions(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, totalRecords(bankFilter));
    }

    /**
     * Creates constraints for pagination
     *
     * @param bankFilter
     * @param builder
     * @param root
     * @return
     */
    private Predicate[] createRestrictions(BankFilter bankFilter, CriteriaBuilder builder, Root<Bank> root) {
        List<Predicate> predicates = new ArrayList<>();

        if (!StringUtils.isEmpty(bankFilter.getCode())) {
            predicates.add(builder.like(builder.lower(root.get("code")), "%" + bankFilter.getCode().toLowerCase() + "%"));
        }

        if (!StringUtils.isEmpty(bankFilter.getName())) {
            predicates.add(builder.like(builder.lower(root.get("name")), "%" + bankFilter.getName().toLowerCase() + "%"));
        }
        return predicates.toArray(new Predicate[predicates.size()]);
    }

    /**
     * Adds restrictions to data paging
     *
     * @param query
     * @param pageable
     */
    private void pagingRestrictions(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRegistration = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRegistration);
        query.setMaxResults(totalRecordsPerPage);
    }

    /**
     * Retrieve the number of records
     *
     * @param bankFilter
     * @return
     */
    private Long totalRecords(BankFilter bankFilter) {
        CriteriaBuilder builder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
        Root<Bank> root = criteria.from(Bank.class);

        Predicate[] predicates = createRestrictions(bankFilter, builder, root);
        criteria.where(predicates);

        criteria.select(builder.count(root));
        return manager.createQuery(criteria).getSingleResult();
    }
}
