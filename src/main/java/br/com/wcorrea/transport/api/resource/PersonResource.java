package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Person;
import br.com.wcorrea.transport.api.repository.PersonRepository;
import br.com.wcorrea.transport.api.repository.filter.PersonFilter;
import br.com.wcorrea.transport.api.service.PersonService;
import br.com.wcorrea.transport.api.service.exception.PersonNotFound;
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
 * Class responsible for providing all the resources needed to handle persons
 */
@RestController
@RequestMapping("/persons")
public class PersonResource {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PersonService personService;

    /**
     * Retrieve the list of registered person
     *
     * @return List of persons
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_PERSON') and #oauth2.hasScope('read')")
    public Page<Person> findAll(PersonFilter personFilter, Pageable paginavel) {
        return personRepository.findAll(personFilter, paginavel);
    }

    /**
     * Retrieve a specific person
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LIST_PERSON') and #oauth2.hasScope('read')")
    public ResponseEntity<Person> findOne(@Valid @PathVariable String key) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            Person personFound = personRepository.findOne(id);
            return personFound != null ? ResponseEntity.ok(personFound) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new PersonNotFound();
        }

    }

    /**
     * Save a new person
     *
     * @param Person person
     * @param response         HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> save(@Valid @RequestBody Person Person, HttpServletResponse response) {
        br.com.wcorrea.transport.api.model.Person salvedPerson = personRepository.saveAndFlush(Person);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedPerson.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedPerson);
    }

    /**
     * Update person
     *
     * @param Person Person
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Person> update(@Valid @PathVariable String key, @Valid @RequestBody Person Person) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            return ResponseEntity.status(HttpStatus.OK).body(personService.update(id, Person));
        } catch (Exception e) {
            throw new PersonNotFound();
        }
    }

    /**
     * Delete person
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_PERSON') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            personRepository.delete(id);
        } catch (Exception e) {
            throw new PersonNotFound();
        }

    }
}
