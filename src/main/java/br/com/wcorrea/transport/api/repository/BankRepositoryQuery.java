package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Bank;
import br.com.wcorrea.transport.api.repository.filter.BankFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BankRepositoryQuery {
    Page<Bank> findAll(BankFilter bankFilter, Pageable pageable);
}
