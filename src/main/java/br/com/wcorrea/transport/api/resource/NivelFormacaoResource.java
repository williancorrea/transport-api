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
import java.util.Optional;

@RestController
@RequestMapping("/niveis-formacao")
public class NivelFormacaoResource {

    @Autowired
    private NivelFormacaoRepository nivelFormacaoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private NivelFormacaoService nivelFormacaoService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTA_NIVEL-FORMACAO') and #oauth2.hasScope('read')")
    public Page<NivelFormacao> findAll(NivelFormacaoFilter nivelFormacaoFilter, Pageable pageable) {
        return nivelFormacaoRepository.findAll(nivelFormacaoFilter, pageable);
    }

    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTA_NIVEL-FORMACAO') and #oauth2.hasScope('read')")
    public ResponseEntity<NivelFormacao> findOne(@Valid @PathVariable String key) {
        Optional<NivelFormacao> nivelFormacao = nivelFormacaoRepository.findById(nivelFormacaoService.buscarPorKey(key));
        return nivelFormacao.isPresent() ? ResponseEntity.ok(nivelFormacao.get()) : ResponseEntity.notFound().build();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR-NIVEL-FORMACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NivelFormacao> save(@Valid @RequestBody NivelFormacao nivelFormacao, HttpServletResponse response) {
        NivelFormacao nivelFormacaoSalvo = nivelFormacaoRepository.saveAndFlush(nivelFormacao);
        publisher.publishEvent(new EventResourceCreated(this, response, nivelFormacaoSalvo.getId().toString()));
        return ResponseEntity.status(HttpStatus.CREATED).body(nivelFormacaoSalvo);
    }

    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_NIVEL-FORMACAO') and #oauth2.hasScope('write')")
    public ResponseEntity<NivelFormacao> update(@Valid @PathVariable String key, @Valid @RequestBody NivelFormacao nivelFormacao) {
        return ResponseEntity.status(HttpStatus.OK).body(nivelFormacaoService.update(nivelFormacaoService.buscarPorKey(key), nivelFormacao));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_NIVEL-FORMACAO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        nivelFormacaoRepository.deleteById(nivelFormacaoService.buscarPorKey(key));
    }
}
