package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Person;
import br.com.wcorrea.transport.api.repository.filter.PersonFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PersonRepositoryQuery {
    Page<Person> findAll(PersonFilter personFilter, Pageable pageable);
}
