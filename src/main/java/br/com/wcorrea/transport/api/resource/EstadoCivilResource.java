package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.EstadoCivil;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilFiltro;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.service.EstadoCivilService;
import br.com.wcorrea.transport.api.utils.Criptografia;
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
@RequestMapping("/estado-civil")
public class EstadoCivilResource {

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private EstadoCivilService estadoCivilService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE ESTADO CIVIL
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ESTADO_CIVIL') and #oauth2.hasScope('read')")
    public Page<EstadoCivil> findAll(EstadoCivilFiltro filtro, Pageable paginacao) {
        return estadoCivilRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM ESTADO CIVIL ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ESTADO_CIVIL') and #oauth2.hasScope('read')")
    public ResponseEntity<EstadoCivil> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(estadoCivilService.buscarPorId(estadoCivilService.buscarPorKey(key)));
    }

    /**
     * SALVA UM ESTADO CIVIL
     *
     * @param estadoCivil
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_ESTADO_CIVIL') and #oauth2.hasScope('write')")
    public ResponseEntity<EstadoCivil> save(@Valid @RequestBody EstadoCivil estadoCivil, HttpServletResponse response) {
        EstadoCivil estadoCivilSalvo = estadoCivilRepository.saveAndFlush(estadoCivil);
        publisher.publishEvent(new EventResourceCreated(this, response, estadoCivilSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(estadoCivilSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param estadoCivil
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_ESTADO_CIVIL') and #oauth2.hasScope('write')")
    public ResponseEntity<EstadoCivil> update(@Valid @PathVariable String key, @Valid @RequestBody EstadoCivil estadoCivil) {
        return ResponseEntity.status(HttpStatus.OK).body(estadoCivilService.atualizar(estadoCivilService.buscarPorKey(key), estadoCivil));
    }

    /**
     * REMOVE O OBJETO PASSADO
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_ESTADO_CIVIL') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        EstadoCivil estadoCivil = estadoCivilService.buscarPorId(estadoCivilService.buscarPorKey(key));
        estadoCivilRepository.delete(estadoCivil.getId());
    }
}
