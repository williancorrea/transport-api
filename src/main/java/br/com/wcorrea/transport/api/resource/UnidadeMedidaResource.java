package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.UnidadeMedida;
import br.com.wcorrea.transport.api.repository.UnidadeMedida.UnidadeMedidaRepository;
import br.com.wcorrea.transport.api.repository.UnidadeMedida.UnidadeMedidaFilter;
import br.com.wcorrea.transport.api.service.UnidadeMedidaService;
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
 * Class responsible for providing all the resources needed to handle product unit trash
 */
@RestController
@RequestMapping("/unidade-medidas")
public class UnidadeMedidaResource {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    /**
     * Retrieve the list of registered product units
     *
     * @return List of product units
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_UNIDADE-MEDIDA') and #oauth2.hasScope('read')")
    public Page<UnidadeMedida> findAll(UnidadeMedidaFilter unidadeMedidaFilter, Pageable pageable) {
        return unidadeMedidaRepository.findAll(unidadeMedidaFilter, pageable);
    }

    /**
     * Retrieve a specific product unit
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_UNIDADE-MEDIDA') and #oauth2.hasScope('read')")
    public ResponseEntity<UnidadeMedida> findOne(@Valid @PathVariable String key) {
        UnidadeMedida unidadeMedidaFound = unidadeMedidaRepository.findOne(unidadeMedidaService.buscarPorKey(key));
        return unidadeMedidaFound != null ? ResponseEntity.ok(unidadeMedidaFound) : ResponseEntity.notFound().build();
    }

    /**
     * Save a new product unit
     *
     * @param unidadeMedida Product Unit
     * @param response    HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_UNIDADE-MEDIDA') and #oauth2.hasScope('write')")
    public ResponseEntity<UnidadeMedida> save(@Valid @RequestBody UnidadeMedida unidadeMedida, HttpServletResponse response) {
        UnidadeMedida salvedUnidadeMedida = unidadeMedidaRepository.saveAndFlush(unidadeMedida);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedUnidadeMedida.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedUnidadeMedida);
    }

    /**
     * Update product unit
     *
     * @param unidadeMedida UnidadeMedida
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_UNIDADE-MEDIDA') and #oauth2.hasScope('write')")
    public ResponseEntity<UnidadeMedida> update(@Valid @PathVariable String key, @Valid @RequestBody UnidadeMedida unidadeMedida) {
        return ResponseEntity.status(HttpStatus.OK).body(unidadeMedidaService.update(unidadeMedidaService.buscarPorKey(key), unidadeMedida));
    }

    /**
     * Delete product unit
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_UNIDADE-MEDIDA') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        unidadeMedidaRepository.delete(unidadeMedidaService.buscarPorKey(key));
    }
}
