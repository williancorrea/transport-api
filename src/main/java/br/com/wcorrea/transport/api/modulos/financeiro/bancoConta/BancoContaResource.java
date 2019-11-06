package br.com.wcorrea.transport.api.modulos.financeiro.bancoConta;

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
@RequestMapping("financeiro/bancos-contas")
public class BancoContaResource {

    @Autowired
    private BancoContaRepository bancoContaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private BancoContaService bancoContaService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<BancoConta> findAll(BancoContaRepositoryFiltro bancoContaRepositoryFiltro, Pageable pageable) {
        return bancoContaRepository.findAll(bancoContaRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<BancoConta> findOne(@Valid @PathVariable String key) {
        BancoConta bancoContaEncontrado = bancoContaService.buscarPorId(bancoContaService.buscarPorKey(key));
        return bancoContaEncontrado != null ? ResponseEntity.ok(bancoContaEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<BancoConta> save(@Valid @RequestBody BancoConta bancoConta, HttpServletResponse response) throws Exception {
        bancoConta = bancoContaService.salvar(bancoConta);
        publisher.publishEvent(new EventResourceCreated(this, response, bancoConta.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoConta);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<BancoConta> update(@Valid @PathVariable String key, @Valid @RequestBody BancoConta bancoConta) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoContaService.atualizar(bancoContaService.buscarPorKey(key), bancoConta));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        bancoContaRepository.deleteById(bancoContaService.buscarPorKey(key));
    }
}
