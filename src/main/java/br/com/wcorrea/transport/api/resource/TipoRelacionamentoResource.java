package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.TipoRelacionamento;
import br.com.wcorrea.transport.api.repository.TipoRelacionamento.TipoRelacionamentoRepository;
import br.com.wcorrea.transport.api.repository.TipoRelacionamento.TipoRelacionamentoFilter;
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

/**
 * Classe responsavel por prover todos os recursos necessarios para manipular um tipo de relacionamento
 */
@RestController
@RequestMapping("/types-of-relationships")
public class TipoRelacionamentoResource {

    @Autowired
    private TipoRelacionamentoRepository tipoRelacionamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TipoRelacionamentoService tipoRelacionamentoService;

    /**
     * Recupera uma lista de registros do tipo de relacionamento
     *
     * @return List of types of relationships
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('read')")
    public Page<TipoRelacionamento> findAll(TipoRelacionamentoFilter tipoRelacionamentoFilter, Pageable pageable) {
        return tipoRelacionamentoRepository.findAll(tipoRelacionamentoFilter, pageable);
    }

    /**
     * Recupera um tipo de relacionamento especifico
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<TipoRelacionamento> findOne(@Valid @PathVariable String key) {
        TipoRelacionamento tipoRelacionamentoFound = tipoRelacionamentoRepository.findOne(tipoRelacionamentoService.buscarPorKey(key));
        return tipoRelacionamentoFound != null ? ResponseEntity.ok(tipoRelacionamentoFound) : ResponseEntity.notFound().build();
    }

    /**
     * Salvar um novo tipo de relacionamento
     *
     * @param TipoRelacionamento types of relationship
     * @param response           HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoRelacionamento> save(@Valid @RequestBody TipoRelacionamento TipoRelacionamento, HttpServletResponse response) {
        TipoRelacionamento salvedTipoRelacionamento = tipoRelacionamentoRepository.saveAndFlush(TipoRelacionamento);
        publisher.publishEvent(new EventResourceCreated(this, response, salvedTipoRelacionamento.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(salvedTipoRelacionamento);
    }

    /**
     * Atualizar tipo de relacionamento
     *
     * @param TipoRelacionamento TipoRelacionamento
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TIPO-RELACIONAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoRelacionamento> update(@Valid @PathVariable String key, @Valid @RequestBody TipoRelacionamento TipoRelacionamento) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoRelacionamentoService.update(tipoRelacionamentoService.buscarPorKey(key), TipoRelacionamento));
    }

    /**
     * Excluir um tipo de relacionamento
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_TIPO-RELACIONAMENTO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        tipoRelacionamentoRepository.delete(tipoRelacionamentoService.buscarPorKey(key));
    }
}
