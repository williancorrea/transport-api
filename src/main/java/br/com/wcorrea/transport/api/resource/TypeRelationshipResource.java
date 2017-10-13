package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.TypeRelationship;
import br.com.wcorrea.transport.api.repository.TypeRelationshipRepository;
import br.com.wcorrea.transport.api.service.TypeRelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

/**
 * Class responsible for providing all the resources needed to handle type of relationships trash
 */
@RestController
@RequestMapping("/type-of-relationship")
public class TypeRelationshipResource {

    @Autowired
    private TypeRelationshipRepository typeRelationshipRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TypeRelationshipService typeRelationshipService;

    /**
     * Retrieve the list of registered type of relationships
     *
     * @return List of type of relationships
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_TYPE-RELATIONSHIP') and #oauth2.hasScope('read')")
    public List<TypeRelationship> listAllBank() {
        return typeRelationshipRepository.findAll();
    }

    /**
     * Retrieve a specific type of relationship
     *
     * @param id type of relationship id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIST_TYPE-RELATIONSHIP') and #oauth2.hasScope('read')")
    public ResponseEntity<TypeRelationship> findOneBank(@Valid @PathVariable Long id) {
        TypeRelationship typeRelationship = typeRelationshipRepository.findOne(id);
        return typeRelationship != null ? ResponseEntity.ok(typeRelationship) : ResponseEntity.notFound().build();
    }

    /**
     * Save a new type of relationship
     *
     * @param typeRelationship  Bank
     * @param response HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_TYPE-RELATIONSHIP') and #oauth2.hasScope('write')")
    public ResponseEntity<TypeRelationship> save(@Valid @RequestBody TypeRelationship typeRelationship, HttpServletResponse response) {
        TypeRelationship saveTypeRelationship = typeRelationshipRepository.save(typeRelationship);
        publisher.publishEvent(new EventResourceCreated(this, response, saveTypeRelationship.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(saveTypeRelationship);
    }

    /**
     * Update type of relationship
     *
     * @param typeRelationship  type of relationship
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_TYPE-RELATIONSHIP') and #oauth2.hasScope('write')")
    public ResponseEntity<TypeRelationship> update(@Valid @PathVariable Long id, @Valid @RequestBody TypeRelationship typeRelationship) {
        TypeRelationship updateBank = typeRelationshipService.update(id, typeRelationship);
        return ResponseEntity.status(HttpStatus.OK).body(updateBank);
    }

    /**
     * Delete type of relationship
     *
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_TYPE-RELATIONSHIP') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long id) {
        typeRelationshipRepository.delete(id);
    }
}
