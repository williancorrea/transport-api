package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.LevelOfEducation;
import br.com.wcorrea.transport.api.repository.LevelOfEducationRepository;
import br.com.wcorrea.transport.api.repository.filter.LevelOfEducationFilter;
import br.com.wcorrea.transport.api.service.LevelOfEducationService;
import br.com.wcorrea.transport.api.service.exception.LevelOfEducationNotFound;
import br.com.wcorrea.transport.api.utils.Criptografia;
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
@RequestMapping("/levels-of-education")
public class LevelOfEducationResource {

    @Autowired
    private LevelOfEducationRepository levelOfEducationRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private LevelOfEducationService levelOfEducationService;

    /**
     * Retrieve the list of registered level of education
     *
     * @return List of level of educations
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_LEVEL-OF-EDUCATION') and #oauth2.hasScope('read')")
    public Page<LevelOfEducation> findAll(LevelOfEducationFilter levelOfEducationFilter, Pageable pageable) {
        return levelOfEducationRepository.findAll(levelOfEducationFilter, pageable);
    }

    /**
     * Retrieve a specific level of education
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LIST_LEVEL-OF-EDUCATION') and #oauth2.hasScope('read')")
    public ResponseEntity<LevelOfEducation> findOne(@Valid @PathVariable String key) {
        try {
            Long id = Long.parseLong(new Criptografia().decryptFromHex(key));
            LevelOfEducation LevelOfEducationFound = levelOfEducationRepository.findOne(id);
            return LevelOfEducationFound != null ? ResponseEntity.ok(LevelOfEducationFound) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            throw new LevelOfEducationNotFound();
        }

    }

    /**
     * Save a new level of education
     *
     * @param LevelOfEducation level of education
     * @param response         HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_LEVEL-OF-EDUCATION') and #oauth2.hasScope('write')")
    public ResponseEntity<LevelOfEducation> save(@Valid @RequestBody LevelOfEducation LevelOfEducation, HttpServletResponse response) {
        LevelOfEducation salvedLevelOfEducation = levelOfEducationRepository.saveAndFlush(LevelOfEducation);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedLevelOfEducation.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedLevelOfEducation);
    }

    /**
     * Update level of education
     *
     * @param LevelOfEducation LevelOfEducation
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_LEVEL-OF-EDUCATION') and #oauth2.hasScope('write')")
    public ResponseEntity<LevelOfEducation> update(@Valid @PathVariable String key, @Valid @RequestBody LevelOfEducation LevelOfEducation) {
        try {
            Long id = Long.parseLong(new Criptografia().decryptFromHex(key));
            return ResponseEntity.status(HttpStatus.OK).body(levelOfEducationService.update(id, LevelOfEducation));
        } catch (Exception e) {
            throw new LevelOfEducationNotFound();
        }
    }

    /**
     * Delete level of education
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_LEVEL-OF-EDUCATION') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        try {
            Long id = Long.parseLong(new Criptografia().decryptFromHex(key));
            levelOfEducationRepository.delete(id);
        } catch (Exception e) {
            throw new LevelOfEducationNotFound();
        }

    }
}
