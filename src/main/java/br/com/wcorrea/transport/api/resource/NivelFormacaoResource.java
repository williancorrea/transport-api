package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.NivelFormacao;
import br.com.wcorrea.transport.api.repository.NivelFormacao.NivelFormacaoFilter;
import br.com.wcorrea.transport.api.repository.NivelFormacao.NivelFormacaoRepository;
import br.com.wcorrea.transport.api.service.NivelFormacaoService;
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
@RequestMapping("/niveis-formacao")
public class NivelFormacaoResource {

    @Autowired
    private NivelFormacaoRepository nivelFormacaoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private NivelFormacaoService nivelFormacaoService;

    /**
     * recupera a lista de nives de educacao
     *
     * @return List of level of educations
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTA_NIVEL-FORMACAO') and #oauth2.hasScope('read')")
    public Page<NivelFormacao> findAll(NivelFormacaoFilter nivelFormacaoFilter, Pageable pageable) {
        return nivelFormacaoRepository.findAll(nivelFormacaoFilter, pageable);
    }

    /**
     * recupera um nivel de formacao especifico
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTA_NIVEL-FORMACAO') and #oauth2.hasScope('read')")
    public ResponseEntity<NivelFormacao> findOne(@Valid @PathVariable String key) {
        NivelFormacao nivelFormacao = nivelFormacaoRepository.findOne(nivelFormacaoService.buscarPorKey(key));
        return nivelFormacao != null ? ResponseEntity.ok(nivelFormacao) : ResponseEntity.notFound().build();
    }

    /**
     * Salva um novo nivel de formacao
     *
     * @param nivelFormacao level of education
     * @param response      HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR-NIVEL-FORMACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NivelFormacao> save(@Valid @RequestBody NivelFormacao nivelFormacao, HttpServletResponse response) {
        NivelFormacao nivelFormacaoSalvo = nivelFormacaoRepository.saveAndFlush(nivelFormacao);
        publisher.publishEvent(new EventResourceCreated(this, response, nivelFormacaoSalvo.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(nivelFormacaoSalvo);
    }

    /**
     * Atualizar nivel de formacao
     *
     * @param nivelFormacao NivelFormacao
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_NIVEL-FORMACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NivelFormacao> update(@Valid @PathVariable String key, @Valid @RequestBody NivelFormacao nivelFormacao) {
        return ResponseEntity.status(HttpStatus.OK).body(nivelFormacaoService.update(nivelFormacaoService.buscarPorKey(key), nivelFormacao));
    }

    /**
     * Deletar nivel de formacao
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_NIVEL-FORMACAO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        nivelFormacaoRepository.delete(nivelFormacaoService.buscarPorKey(key));
    }
}
