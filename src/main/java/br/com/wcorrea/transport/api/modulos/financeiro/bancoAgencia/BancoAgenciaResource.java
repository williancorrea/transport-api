package br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia;

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
@RequestMapping("/bancos-agencias")
public class BancoAgenciaResource {

    @Autowired
    private BancoAgenciaRepository bancoAgenciaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private BancoAgenciaService bancoAgenciaService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<BancoAgencia> findAll(BancoAgenciaRepositoryFiltro bancoAgenciaRepositoryFiltro, Pageable pageable) {
        return bancoAgenciaRepository.findAll(bancoAgenciaRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<BancoAgencia> findOne(@Valid @PathVariable String key) {
        BancoAgencia bancoAgenciaEncontrado = bancoAgenciaService.findOne(bancoAgenciaService.buscarPorKey(key));
        return bancoAgenciaEncontrado != null ? ResponseEntity.ok(bancoAgenciaEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<BancoAgencia> save(@Valid @RequestBody BancoAgencia bancoAgencia, HttpServletResponse response) throws Exception {
        bancoAgencia = bancoAgenciaService.salvar(bancoAgencia);
        publisher.publishEvent(new EventResourceCreated(this, response, bancoAgencia.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoAgencia);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<BancoAgencia> update(@Valid @PathVariable String key, @Valid @RequestBody BancoAgencia bancoAgencia) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoAgenciaService.atualizar(bancoAgenciaService.buscarPorKey(key), bancoAgencia));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        bancoAgenciaRepository.deleteById(bancoAgenciaService.buscarPorKey(key));
    }
}
