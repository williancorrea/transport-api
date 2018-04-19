package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.MaritalStatus;
import br.com.wcorrea.transport.api.repository.MaritalStatusRepository;
import br.com.wcorrea.transport.api.repository.filter.MaritalStatusFilter;
import br.com.wcorrea.transport.api.service.MaritalStatusService;
import br.com.wcorrea.transport.api.service.exception.MaritalStatusNotFound;
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

@RestController
@RequestMapping("/marital_status")
public class MaritalStatusResource {

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private MaritalStatusService maritalStatusService;

    /**
     * Retrieve the list of registered marital status
     *
     * @return List of marital status
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_MARITAL_STATUS') and #oauth2.hasScope('read')")
    public Page<MaritalStatus> findAll(MaritalStatusFilter maritalStatusFilter, Pageable pageable) {
        return maritalStatusRepository.findAll(maritalStatusFilter, pageable);
    }

    /**
     * Retrieve a specific marital status
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LIST_MARITAL_STATUS') and #oauth2.hasScope('read')")
    public ResponseEntity<MaritalStatus> findOne(@Valid @PathVariable String key) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            MaritalStatus MaritalStatusFound = maritalStatusRepository.findOne(id);
            return MaritalStatusFound != null ? ResponseEntity.ok(MaritalStatusFound) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new MaritalStatusNotFound();
        }

    }

    /**
     * Save a new marital status
     *
     * @param MaritalStatus marital status
     * @param response         HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_MARITAL_STATUS') and #oauth2.hasScope('write')")
    public ResponseEntity<MaritalStatus> save(@Valid @RequestBody MaritalStatus MaritalStatus, HttpServletResponse response) {
        MaritalStatus salvedMaritalStatus = maritalStatusRepository.saveAndFlush(MaritalStatus);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedMaritalStatus.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedMaritalStatus);
    }

    /**
     * Update marital status
     *
     * @param MaritalStatus MaritalStatus
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_MARITAL_STATUS') and #oauth2.hasScope('write')")
    public ResponseEntity<MaritalStatus> update(@Valid @PathVariable String key, @Valid @RequestBody MaritalStatus MaritalStatus) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            return ResponseEntity.status(HttpStatus.OK).body(maritalStatusService.update(id, MaritalStatus));
        } catch (Exception e) {
            throw new MaritalStatusNotFound();
        }
    }

    /**
     * Delete marital status
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_MARITAL_STATUS') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        try {
            Long id = Long.parseLong(new Cryptography().decryptFromHex(key));
            maritalStatusRepository.delete(id);
        } catch (Exception e) {
            throw new MaritalStatusNotFound();
        }

    }
}
