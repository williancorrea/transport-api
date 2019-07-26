package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Banco;
import br.com.wcorrea.transport.api.repository.banco.BancoFiltro;
import br.com.wcorrea.transport.api.repository.banco.BancoRepository;
import br.com.wcorrea.transport.api.service.BancoService;
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
@RequestMapping("/fretamentos")
public class FretamentoResource {

//    @Autowired
//    private BancoRepository bancoRepository;
//
//    @Autowired
//    private ApplicationEventPublisher publisher;
//
//    @Autowired
//    private BancoService bancoService;
//
//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
//    public Page<Banco> findAll(BancoFiltro bancoFiltro, Pageable pageable) {
//        return bancoRepository.findAll(bancoFiltro, pageable);
//    }
//
    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<String> findOne(@Valid @PathVariable String key) {
//        Banco bancoEncontrado = bancoService.findOne(bancoService.buscarPorKey(key));
//        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
        return ResponseEntity.ok("ACHO QUE DEU " + key);
    }
//
//    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
//    public ResponseEntity<Banco> save(@Valid @RequestBody Banco banco, HttpServletResponse response) throws Exception {
//        banco = bancoRepository.saveAndFlush(banco);
//        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
//        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
//    }
//
//    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
//    public ResponseEntity<Banco> update(@Valid @PathVariable String key, @Valid @RequestBody Banco banco) {
//        return ResponseEntity.status(HttpStatus.OK).body(bancoService.update(bancoService.buscarPorKey(key), banco));
//    }
//
//    @DeleteMapping("/{key}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
//    public void delete(@PathVariable String key) {
//        bancoRepository.delete(bancoService.buscarPorKey(key));
//    }
}
