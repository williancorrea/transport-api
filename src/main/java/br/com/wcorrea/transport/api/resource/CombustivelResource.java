package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Combustivel;
import br.com.wcorrea.transport.api.repository.combustivel.CombustivelFiltro;
import br.com.wcorrea.transport.api.repository.combustivel.CombustivelRepository;
import br.com.wcorrea.transport.api.service.CombustivelService;
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
 * Classe responsavel por fornecer todos os recursos para manipular os dados de Combustivels
 */
@RestController
@RequestMapping("/combustivel")
public class CombustivelResource {

    @Autowired
    private CombustivelRepository combustivelRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CombustivelService combustivelService;

    /**
     * Recupera a lista de registros dos combustivel
     *
     * @return List of country
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<Combustivel> findAll(CombustivelFiltro combustivelFiltro, Pageable pageable) {
        return combustivelRepository.findAll(combustivelFiltro, pageable);
    }

    /**
     * Recupera um combustivel especifico
     *
     * @param key country id
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<Combustivel> findOne(@Valid @PathVariable String key) {
        Combustivel combustivelEncontrado = combustivelService.findOne(combustivelService.buscarPorKey(key));
        return combustivelEncontrado != null ? ResponseEntity.ok(combustivelEncontrado) : ResponseEntity.notFound().build();
    }

    /**
     * Salva um novo Combustivel
     *
     * @param combustivel Combustivel
     * @param response    HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<Combustivel> save(@Valid @RequestBody Combustivel combustivel, HttpServletResponse response) throws Exception {
        combustivel = combustivelRepository.saveAndFlush(combustivel);
        publisher.publishEvent(new EventResourceCreated(this, response, combustivel.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(combustivel);
    }

    /**
     * Atualizar combustivel
     *
     * @param combustivel Combustivel
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<Combustivel> update(@Valid @PathVariable String key, @Valid @RequestBody Combustivel combustivel) {
        return ResponseEntity.status(HttpStatus.OK).body(combustivelService.update(combustivelService.buscarPorKey(key), combustivel));
    }

    /**
     * Deletar Combustivel
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        combustivelRepository.delete(combustivelService.buscarPorKey(key));
    }
}
