package br.com.wcorrea.transport.api.modulos.financeiro.chequeRecebido;

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
@RequestMapping("/financeiro/cheques-recebidos")
public class ChequeRecebidoResource {

    @Autowired
    private ChequeRecebidoRepository chequeRecebidoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ChequeRecebidoService chequeRecebidoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<ChequeRecebido> findAll(ChequeRecebidoRepositoryFiltro bancoRepositoryFiltro, Pageable pageable) {
        return chequeRecebidoRepository.findAll(bancoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<ChequeRecebido> findOne(@Valid @PathVariable String key) {
        ChequeRecebido bancoEncontrado = chequeRecebidoService.buscarPorId(chequeRecebidoService.buscarPorKey(key));
        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<ChequeRecebido> save(@Valid @RequestBody ChequeRecebido banco, HttpServletResponse response) throws Exception {
        banco = chequeRecebidoService.salvar(banco);
        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<ChequeRecebido> update(@Valid @PathVariable String key, @Valid @RequestBody ChequeRecebido banco) {
        return ResponseEntity.status(HttpStatus.OK).body(chequeRecebidoService.update(chequeRecebidoService.buscarPorKey(key), banco));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        chequeRecebidoRepository.deleteById(chequeRecebidoService.buscarPorKey(key));
    }
}
