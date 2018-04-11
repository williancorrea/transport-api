package br.com.wcorrea.transport.api.repository;

import org.springframework.data.domain.Pageable;

import javax.persistence.TypedQuery;

public class UtilsRepository {
    /**
     * Adds restrictions to data paging
     *
     * @param query
     * @param pageable
     */
    public static void pagingRestrictions(TypedQuery<?> query, Pageable pageable) {
        int currentPage = pageable.getPageNumber();
        int totalRecordsPerPage = pageable.getPageSize();
        int firstPageRegistration = currentPage * totalRecordsPerPage;

        query.setFirstResult(firstPageRegistration);
        query.setMaxResults(totalRecordsPerPage);
    }
}
