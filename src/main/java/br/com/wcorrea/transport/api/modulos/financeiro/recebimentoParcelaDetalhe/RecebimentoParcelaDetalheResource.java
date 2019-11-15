package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaDetalhe;

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
public class RecebimentoParcelaDetalheResource {

    @Autowired
    private RecebimentoParcelaDetalheRepository recebimentoParcelaDetalheRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RecebimentoParcelaDetalheService recebimentoParcelaDetalheService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<RecebimentoParcelaDetalhe> findAll(RecebimentoParcelaDetalheRepositoryFiltro bancoRepositoryFiltro, Pageable pageable) {
        return recebimentoParcelaDetalheRepository.findAll(bancoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<RecebimentoParcelaDetalhe> findOne(@Valid @PathVariable String key) {
        RecebimentoParcelaDetalhe bancoEncontrado = recebimentoParcelaDetalheService.buscarPorKey(key);
        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoParcelaDetalhe> save(@Valid @RequestBody RecebimentoParcelaDetalhe banco, HttpServletResponse response) throws Exception {
        banco = recebimentoParcelaDetalheService.salvar(banco);
        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoParcelaDetalhe> update(@Valid @PathVariable String key, @Valid @RequestBody RecebimentoParcelaDetalhe banco) {
        return ResponseEntity.status(HttpStatus.OK).body(recebimentoParcelaDetalheService.update(key, banco));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        recebimentoParcelaDetalheRepository.deleteById(recebimentoParcelaDetalheService.buscarPorKey(key).getId());
    }
}
