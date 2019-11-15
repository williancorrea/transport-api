package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus;

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
@RequestMapping("/financeiro/recebimento-parcela-status")
public class RecebimentoParcelaStatusResource {

    @Autowired
    private RecebimentoParcelaStatusRepository recebimentoParcelaStatusRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RecebimentoParcelaStatusService recebimentoParcelaStatusService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<RecebimentoParcelaStatus> findAll(RecebimentoParcelaStatusRepositoryFiltro bancoRepositoryFiltro, Pageable pageable) {
        return recebimentoParcelaStatusRepository.findAll(bancoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<RecebimentoParcelaStatus> findOne(@Valid @PathVariable String key) {
        RecebimentoParcelaStatus bancoEncontrado = recebimentoParcelaStatusService.buscarPorId(recebimentoParcelaStatusService.buscarPorKey(key));
        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoParcelaStatus> save(@Valid @RequestBody RecebimentoParcelaStatus banco, HttpServletResponse response) throws Exception {
        banco = recebimentoParcelaStatusService.salvar(banco);
        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoParcelaStatus> update(@Valid @PathVariable String key, @Valid @RequestBody RecebimentoParcelaStatus banco) {
        return ResponseEntity.status(HttpStatus.OK).body(recebimentoParcelaStatusService.update(recebimentoParcelaStatusService.buscarPorKey(key), banco));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        recebimentoParcelaStatusRepository.deleteById(recebimentoParcelaStatusService.buscarPorKey(key));
    }
}
