package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.TipoRelacionamento;
import br.com.wcorrea.transport.api.repository.TipoRelacionamento.TipoRelacionamentoFilter;
import br.com.wcorrea.transport.api.repository.TipoRelacionamento.TipoRelacionamentoRepository;
import br.com.wcorrea.transport.api.service.TipoRelacionamentoService;
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
import java.util.Optional;

@RestController
@RequestMapping("/types-of-relationships")
public class TipoRelacionamentoResource {

    @Autowired
    private TipoRelacionamentoRepository tipoRelacionamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TipoRelacionamentoService tipoRelacionamentoService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('read')")
    public Page<TipoRelacionamento> findAll(TipoRelacionamentoFilter tipoRelacionamentoFilter, Pageable pageable) {
        return tipoRelacionamentoRepository.findAll(tipoRelacionamentoFilter, pageable);
    }

    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<TipoRelacionamento> findOne(@Valid @PathVariable String key) {
        Optional<TipoRelacionamento> tipoRelacionamentoFound = tipoRelacionamentoRepository.findById(tipoRelacionamentoService.buscarPorKey(key));
        return tipoRelacionamentoFound.isPresent() ? ResponseEntity.ok(tipoRelacionamentoFound.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoRelacionamento> save(@Valid @RequestBody TipoRelacionamento TipoRelacionamento, HttpServletResponse response) {
        TipoRelacionamento salvedTipoRelacionamento = tipoRelacionamentoRepository.saveAndFlush(TipoRelacionamento);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedTipoRelacionamento.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedTipoRelacionamento);
    }

    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoRelacionamento> update(@Valid @PathVariable String key, @Valid @RequestBody TipoRelacionamento TipoRelacionamento) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoRelacionamentoService.update(tipoRelacionamentoService.buscarPorKey(key), TipoRelacionamento));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_TIPO-RELACIONAMENTO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        tipoRelacionamentoRepository.deleteById(tipoRelacionamentoService.buscarPorKey(key));
    }
}
