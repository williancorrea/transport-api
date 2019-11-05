package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.TanqueCombustivel;
import br.com.wcorrea.transport.api.repository.veiculo.tanqueCombustivel.TanqueCombustivelFiltro;
import br.com.wcorrea.transport.api.repository.veiculo.tanqueCombustivel.TanqueCombustivelRepository;
import br.com.wcorrea.transport.api.service.TanqueCombustivelService;
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
@RequestMapping("/tanque-combustivel")
public class TanqueCombustivelResource {

    @Autowired
    private TanqueCombustivelRepository tanqueCombustivelRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TanqueCombustivelService tanqueCombustivelService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TANQUE-COMBUSTIVEL') and #oauth2.hasScope('read')")
    public Page<TanqueCombustivel> findAll(TanqueCombustivelFiltro tanqueCombustivelFiltro, Pageable pageable) {
        return tanqueCombustivelRepository.findAll(tanqueCombustivelFiltro, pageable);
    }

    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TANQUE-COMBUSTIVEL') and #oauth2.hasScope('read')")
    public ResponseEntity<TanqueCombustivel> findOne(@Valid @PathVariable String key) {
        TanqueCombustivel tanqueCombustivelEncontrado = tanqueCombustivelService.findOne(tanqueCombustivelService.buscarPorKey(key));
        return tanqueCombustivelEncontrado != null ? ResponseEntity.ok(tanqueCombustivelEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_TANQUE-COMBUSTIVEL') and #oauth2.hasScope('write')")
    public ResponseEntity<TanqueCombustivel> save(@Valid @RequestBody TanqueCombustivel tanqueCombustivel, HttpServletResponse response) throws Exception {
        tanqueCombustivel = tanqueCombustivelRepository.saveAndFlush(tanqueCombustivel);
        publisher.publishEvent(new EventResourceCreated(this, response, tanqueCombustivel.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tanqueCombustivel);
    }

    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TANQUE-COMBUSTIVEL') and #oauth2.hasScope('write')")
    public ResponseEntity<TanqueCombustivel> update(@Valid @PathVariable String key, @Valid @RequestBody TanqueCombustivel tanqueCombustivel) {
        return ResponseEntity.status(HttpStatus.OK).body(tanqueCombustivelService.update(tanqueCombustivelService.buscarPorKey(key), tanqueCombustivel));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_TANQUE-COMBUSTIVEL') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        tanqueCombustivelRepository.deleteById(tanqueCombustivelService.buscarPorKey(key));
    }
}
