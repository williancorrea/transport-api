package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

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
@RequestMapping("/financeiro/recebimento-lancamentos")
public class RecebimentoLancamentoResource {

    @Autowired
    private RecebimentoLancamentoRepository recebimentoLancamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RecebimentoLancamentoService recebimentoLancamentoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<RecebimentoLancamento> findAll(RecebimentoLancamentoRepositoryFiltro bancoRepositoryFiltro, Pageable pageable) {
        return recebimentoLancamentoRepository.findAll(bancoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<RecebimentoLancamento> findOne(@Valid @PathVariable String key) {
        RecebimentoLancamento bancoEncontrado = recebimentoLancamentoService.buscarPorKey(key);
        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoLancamento> save(@Valid @RequestBody RecebimentoLancamento banco, HttpServletResponse response) throws Exception {
        banco = recebimentoLancamentoService.salvar(banco);
        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoLancamento> update(@Valid @PathVariable String key, @Valid @RequestBody RecebimentoLancamento banco) {
        return ResponseEntity.status(HttpStatus.OK).body(recebimentoLancamentoService.update(recebimentoLancamentoService.buscarPorKey(key).getId(), banco));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        recebimentoLancamentoRepository.deleteById(recebimentoLancamentoService.buscarPorKey(key).getId());
    }
}
