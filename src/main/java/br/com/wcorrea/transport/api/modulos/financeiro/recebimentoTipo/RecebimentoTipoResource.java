package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoTipo;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
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
@RequestMapping("/financeiro/recebimento-tipo")
public class RecebimentoTipoResource {

    @Autowired
    private RecebimentoTipoRepository recebimentoTipoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RecebimentoTipoService recebimentoTipoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<RecebimentoTipo> findAll(RecebimentoTipoRepositoryFiltro bancoRepositoryFiltro, Pageable pageable) {
        return recebimentoTipoRepository.findAll(bancoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<RecebimentoTipo> findOne(@Valid @PathVariable String key) {
        RecebimentoTipo bancoEncontrado = recebimentoTipoService.buscarPorId(recebimentoTipoService.buscarPorKey(key));
        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoTipo> save(@Valid @RequestBody RecebimentoTipo banco, HttpServletResponse response) throws Exception {
        banco = recebimentoTipoService.salvar(banco);
        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoTipo> update(@Valid @PathVariable String key, @Valid @RequestBody RecebimentoTipo banco) {
        return ResponseEntity.status(HttpStatus.OK).body(recebimentoTipoService.update(recebimentoTipoService.buscarPorKey(key), banco));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        recebimentoTipoRepository.deleteById(recebimentoTipoService.buscarPorKey(key));
    }
}
