package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/financeiro/recebimento-parcela")
public class RecebimentoParcelaResource {

    @Autowired
    private RecebimentoParcelaRepository recebimentoParcelaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private RecebimentoParcelaService recebimentoParcelaService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<RecebimentoParcela> findAll(RecebimentoParcelaRepositoryFiltro bancoRepositoryFiltro, Pageable pageable) {
        return recebimentoParcelaRepository.findAll(bancoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<RecebimentoParcela> findOne(@Valid @PathVariable String key) {
        RecebimentoParcela objEncontrado = recebimentoParcelaService.buscarPorKey(key);
        return objEncontrado != null ? ResponseEntity.ok(objEncontrado) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<RecebimentoParcela> update(@Valid @PathVariable String key, @Valid @RequestBody RecebimentoParcela obj) {
        return ResponseEntity.status(HttpStatus.OK).body(recebimentoParcelaService.update(key, obj));
    }
}
