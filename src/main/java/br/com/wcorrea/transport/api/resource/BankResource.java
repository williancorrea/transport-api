package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Bank;
import br.com.wcorrea.transport.api.repository.BankRepository;
import br.com.wcorrea.transport.api.repository.filter.BankFilter;
import br.com.wcorrea.transport.api.service.BankService;
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
 * Class responsible for providing all the resources needed to handle bank trash
 */
@RestController
@RequestMapping("/banks")
public class BankResource {

    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private BankService bankService;

    /**
     * Retrieve the list of registered banks'
     *
     * @return List of country
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_BANK') and #oauth2.hasScope('read')")
    public Page<Bank> listAllBank(BankFilter bankFilter, Pageable pageable) {
        return bankRepository.findAll(bankFilter, pageable);
    }

    /**
     * Retrieve a specific bank
     *
     * @param id country id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIST_BANK') and #oauth2.hasScope('read')")
    public ResponseEntity<Bank> findOneBank(@Valid @PathVariable Long id) {
        Bank country = bankRepository.findOne(id);
        return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
    }

    /**
     * Save a new country
     *
     * @param bank     Bank
     * @param response HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_BANK') and #oauth2.hasScope('write')")
    public ResponseEntity<Bank> save(@Valid @RequestBody Bank bank, HttpServletResponse response) {
        Bank salvedBank = bankRepository.save(bank);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedBank.getId()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedBank);
    }

    /**'
     * Update bank
     *
     * @param bank Bank
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_BANK') and #oauth2.hasScope('write')")
    public ResponseEntity<Bank> update(@Valid @PathVariable Long id, @Valid @RequestBody Bank bank) {
        Bank updateBank = bankService.update(id, bank);
        return ResponseEntity.status(HttpStatus.OK).body(updateBank);
    }

    /**
     * Delete country
     *
     * @return
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_BANK') and #oauth2.hasScope('write')")
    public void delete(@PathVariable Long id) {
        bankRepository.delete(id);
    }
}
