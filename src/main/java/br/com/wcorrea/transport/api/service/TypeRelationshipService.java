package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.TypeRelationship;
import br.com.wcorrea.transport.api.repository.TypeRelationshipRepository;
import br.com.wcorrea.transport.api.service.exception.TypeRelationshipUpdateNotFound;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for performing the entire business rule by manipulating type of relationship information
 */
@Service
public class TypeRelationshipService {

    @Autowired
    private TypeRelationshipRepository typeRelationshipRepository;

    /**
     * Update type of relationship
     *
     * @param id
     * @param typeRelationship
     * @return
     */
    public TypeRelationship update(Long id, TypeRelationship typeRelationship) {
        TypeRelationship objFound = typeRelationshipRepository.save(findById(id));
        BeanUtils.copyProperties(typeRelationship, objFound, "id");
        return typeRelationshipRepository.save(objFound);
    }

    /**
     * Find type of relationship by id
     */
    private TypeRelationship findById(Long id) {
        TypeRelationship typeRelationship = typeRelationshipRepository.findOne(id);
        if (typeRelationship == null) {
            throw new TypeRelationshipUpdateNotFound();
        }
        return typeRelationship;
    }
}
