package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.MaritalStatus;
import br.com.wcorrea.transport.api.repository.filter.MaritalStatusFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface MaritalStatusRepositoryQuery {
    Page<MaritalStatus> findAll(MaritalStatusFilter levelOdEducationFilter, Pageable pageable);
}
