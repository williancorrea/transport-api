package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Country;
import br.com.wcorrea.transport.api.model.State;
import br.com.wcorrea.transport.api.repository.CountryRepository;
import br.com.wcorrea.transport.api.repository.StateRepository;
import br.com.wcorrea.transport.api.service.CountryService;
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
 * Class responsible for providing all the resources needed to handle country trash
 */
@RestController
@RequestMapping("/countries")
public class CountryResource {

    @Autowired
    private CountryRepository countryRepository;
    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CountryService countryService;

    /**
     * Retrieve the list of registered countries
     *
     * @return List of country
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_COUNTRY') and #oauth2.hasScope('read')")
    public List<Country> listAllCountry() {
        return countryRepository.findAll();
    }

    /**
     * Retrieve a specific country
     *
     * @param id country id
     * @return
     */
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_LIST_COUNTRY') and #oauth2.hasScope('read')")
    public ResponseEntity<Country> findOneCountry(@Valid @PathVariable Long id) {
        Country country = countryRepository.findOne(id);
        return country != null ? ResponseEntity.ok(country) : ResponseEntity.notFound().build();
    }


    @GetMapping("/{id}/states")
    @PreAuthorize("hasAuthority('ROLE_LIST_COUNTRY') and #oauth2.hasScope('read')")
    public List<State> findAllStates(@Valid @PathVariable Long id) {
//        State state = stateRepository.buscarPorId(id);
//        return state != null ? ResponseEntity.ok(state) : ResponseEntity.notFound().build();
        return stateRepository.findAll();
    }

    /**
     * Save a new country
     *
     * @param country  Country
     * @param response HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_COUNTRY') and #oauth2.hasScope('write')")
    public ResponseEntity<Country> save(@Valid @RequestBody Country country, HttpServletResponse response) {
        Country salvedCountry = countryRepository.save(country);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedCountry.getCountry_id().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedCountry);
    }

    /**
     * Update country
     *
     * @param country  Country
     * @return
     */
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_COUNTRY') and #oauth2.hasScope('write')")
    public ResponseEntity<Country> update(@Valid @PathVariable Long id, @Valid @RequestBody Country country) {
        Country updateCountry = countryService.update(id, country);
        return ResponseEntity.status(HttpStatus.OK).body(updateCountry);
    }



//    /**
//     * Delete country
//     *
//     * @return
//     */
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETE_COUNTRY') and #oauth2.hasScope('write')")
//    public void delete(@PathVariable Long id) {
//        countryRepository.delete(id);
//    }
}
