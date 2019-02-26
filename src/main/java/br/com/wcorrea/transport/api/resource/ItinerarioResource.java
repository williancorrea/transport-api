package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Itinerario;
import br.com.wcorrea.transport.api.repository.itinerario.ItinerarioFiltro;
import br.com.wcorrea.transport.api.repository.itinerario.ItinerarioRepository;
import br.com.wcorrea.transport.api.service.ItinerarioService;
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
@RequestMapping("/itinerario")
public class ItinerarioResource {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ItinerarioService itinerarioService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE ITINERARIO
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ITINERARIO') and #oauth2.hasScope('read')")
    public Page<Itinerario> findAll(ItinerarioFiltro filtro, Pageable paginacao) {
        return itinerarioRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM ITINERARIO ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ITINERARIO') and #oauth2.hasScope('read')")
    public ResponseEntity<Itinerario> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(itinerarioService.buscarPorId(itinerarioService.buscarPorKey(key)));
    }

    /**
     * SALVA UM ITINERARIO
     *
     * @param itinerario
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_ITINERARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Itinerario> save(@Valid @RequestBody Itinerario itinerario, HttpServletResponse response) {
        Itinerario itinerarioSalvo = itinerarioRepository.saveAndFlush(itinerario);
        publisher.publishEvent(new EventResourceCreated(this, response, itinerarioSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(itinerarioSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param itinerario
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_ITINERARIO') and #oauth2.hasScope('write')")
    public ResponseEntity<Itinerario> update(@Valid @PathVariable String key, @Valid @RequestBody Itinerario itinerario) {
        return ResponseEntity.status(HttpStatus.OK).body(itinerarioService.atualizar(itinerarioService.buscarPorKey(key), itinerario));
    }

    /**
     * REMOVE O OBJETO PASSADO
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_ITINERARIO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        Itinerario itinerario = itinerarioService.buscarPorId(itinerarioService.buscarPorKey(key));
        itinerarioRepository.delete(itinerario.getId());
    }

}
