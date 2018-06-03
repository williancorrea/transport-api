package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.TypeRelationship;
import br.com.wcorrea.transport.api.repository.TypeRelationshipRepository;
import br.com.wcorrea.transport.api.service.exception.TypeRelationshipNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Class responsible for performing the entire business rule by manipulating tipo of relationship information
 */
@Service
public class TypeRelationshipService {

    @Autowired
    private TypeRelationshipRepository typeRelationshipRepository;

    /**
     * Update tipo of relationship
     *
     * @param id
     * @param typeRelationship
     * @return
     */
    public TypeRelationship update(Long id, TypeRelationship typeRelationship) {
        TypeRelationship objFound = typeRelationshipRepository.save(findOne(id));
        typeRelationship.setId(objFound.getId());
        typeRelationship.setProperties(objFound.getProperties());
        typeRelationship.getProperties().setDataAlteracao(LocalDateTime.now());
        return typeRelationshipRepository.save(typeRelationship);
    }

    /**
     * Find tipo of relationship by id
     */
    private TypeRelationship findOne(Long id) {
        TypeRelationship typeRelationship = typeRelationshipRepository.findOne(id);
        if (typeRelationship == null) {
            throw new TypeRelationshipNotFound();
        }
        return typeRelationship;
    }
}
