package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Bank;
import br.com.wcorrea.transport.api.repository.BankRepository;
import br.com.wcorrea.transport.api.repository.filter.BankFilter;
import br.com.wcorrea.transport.api.service.BankService;
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
    public Page<Bank> findAll(BankFilter bankFilter, Pageable pageable) {
        return bankRepository.findAll(bankFilter, pageable);
    }

    /**
     * Retrieve a specific bank
     *
     * @param key country id
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LIST_BANK') and #oauth2.hasScope('read')")
    public ResponseEntity<Bank> findOne(@Valid @PathVariable String key) {
        Bank bankFound = bankService.findOne(new Criptografia().getKey(key));
        return bankFound != null ? ResponseEntity.ok(bankFound) : ResponseEntity.notFound().build();
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
    public ResponseEntity<Bank> save(@Valid @RequestBody Bank bank, HttpServletResponse response) throws Exception {
        bank = bankRepository.saveAndFlush(bank);
        publisher.publishEvent(new EventResourceCreated(this, response, bank.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(bank);
    }

    /**
     * Update bank
     *
     * @param bank Bank
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_BANK') and #oauth2.hasScope('write')")
    public ResponseEntity<Bank> update(@Valid @PathVariable String key, @Valid @RequestBody Bank bank) {
        return ResponseEntity.status(HttpStatus.OK).body(bankService.update(new Criptografia().getKey(key), bank));
    }

    /**
     * Delete country
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_BANK') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        bankRepository.delete(new Criptografia().getKey(key));
    }
}
