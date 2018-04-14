package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.TypeRelationship;
import br.com.wcorrea.transport.api.repository.filter.TypeRelationshipFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TypeRelationshipRepositoryQuery {
    Page<TypeRelationship> findAll(TypeRelationshipFilter typeRelationshipFilter, Pageable pageable);
}
