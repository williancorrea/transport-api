package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.TypeRelationship;
import br.com.wcorrea.transport.api.repository.TypeRelationshipRepository;
import br.com.wcorrea.transport.api.repository.filter.TypeRelationshipFilter;
import br.com.wcorrea.transport.api.service.TypeRelationshipService;
import br.com.wcorrea.transport.api.service.exception.TypeRelationshipNotFound;
import br.com.wcorrea.transport.api.utils.Cryptography;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * Class responsible for providing all the resources needed to handle type of relationships trash
 */
@RestController
@RequestMapping("/types-of-relationships")
public class TypeRelationshipResource {

    @Autowired
    private TypeRelationshipRepository typeRelationshipRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TypeRelationshipService typeRelationshipService;

    /**
     * Retrieve the list of registered types of relationship
     *
     * @return List of types of relationships
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_TYPE-RELATIONSHIP') and #oauth2.hasScope('read')")
    public Page<TypeRelationship> findAll(TypeRelationshipFilter typeRelationshipFilter, Pageable pageable) {
        return typeRelationshipRepository.findAll(typeRelationshipFilter, pageable);
    }

    /**
     * Retrieve a specific types of relationship
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LIST_TYPE-RELATIONSHIP') and #oauth2.hasScope('read')")
    public ResponseEntity<TypeRelationship> findOne(@Valid @PathVariable String key) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            TypeRelationship TypeRelationshipFound = typeRelationshipRepository.findOne(id);
            return TypeRelationshipFound != null ? ResponseEntity.ok(TypeRelationshipFound) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new TypeRelationshipNotFound();
        }

    }

    /**
     * Save a new types of relationship
     *
     * @param TypeRelationship types of relationship
     * @param response         HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_TYPE-RELATIONSHIP') and #oauth2.hasScope('write')")
    public ResponseEntity<TypeRelationship> save(@Valid @RequestBody TypeRelationship TypeRelationship, HttpServletResponse response) {
        TypeRelationship salvedTypeRelationship = typeRelationshipRepository.saveAndFlush(TypeRelationship);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedTypeRelationship.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedTypeRelationship);
    }

    /**
     * Update types of relationship
     *
     * @param TypeRelationship TypeRelationship
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_TYPE-RELATIONSHIP') and #oauth2.hasScope('write')")
    public ResponseEntity<TypeRelationship> update(@Valid @PathVariable String key, @Valid @RequestBody TypeRelationship TypeRelationship) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            return ResponseEntity.status(HttpStatus.OK).body(typeRelationshipService.update(id, TypeRelationship));
        } catch (Exception e) {
            throw new TypeRelationshipNotFound();
        }
    }

    /**
     * Delete types of relationship
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_TYPE-RELATIONSHIP') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            typeRelationshipRepository.delete(id);
        } catch (Exception e) {
            throw new TypeRelationshipNotFound();
        }

    }
}
