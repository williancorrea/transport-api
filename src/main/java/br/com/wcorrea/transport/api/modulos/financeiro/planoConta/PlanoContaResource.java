package br.com.wcorrea.transport.api.modulos.financeiro.planoConta;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("/financeiro/plano-conta")
public class PlanoContaResource {

    @Autowired
    private PlanoContaRepository planoContaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PlanoContaService planoContaService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<PlanoConta> findAll(PlanoContaRepositoryFiltro bancoRepositoryFiltro, Pageable pageable) {
        return planoContaRepository.findAll(bancoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<PlanoConta> findOne(@Valid @PathVariable String key) {
        PlanoConta bancoEncontrado = planoContaService.buscarPorId(planoContaService.buscarPorKey(key));
        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<PlanoConta> save(@Valid @RequestBody PlanoConta banco, HttpServletResponse response) throws Exception {
        banco = planoContaService.salvar(banco);
        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<PlanoConta> update(@Valid @PathVariable String key, @Valid @RequestBody PlanoConta banco) {
        return ResponseEntity.status(HttpStatus.OK).body(planoContaService.update(planoContaService.buscarPorKey(key), banco));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        planoContaRepository.deleteById(planoContaService.buscarPorKey(key));
    }
}
