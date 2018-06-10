package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmFiltro;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmRepository;
import br.com.wcorrea.transport.api.service.ControleKmService;
import br.com.wcorrea.transport.api.service.exception.ControleKmNaoEncontrado;
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
@RequestMapping("/controle-km")
public class ControleKmResource {

    @Autowired
    private ControleKmRepository controleKmRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ControleKmService controleKmService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE CONTROLE-KM
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CONTROLE-KM') and #oauth2.hasScope('read')")
    public Page<ControleKm> findAll(ControleKmFiltro filtro, Pageable paginacao) {
        return controleKmRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM CONTROLE-KM ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CONTROLE-KM') and #oauth2.hasScope('read')")
    public ResponseEntity<ControleKm> findOne(@Valid @PathVariable String key) {
        try {
            Long id = Long.parseLong(new Criptografia().decryptFromHex(key));
            return ResponseEntity.ok(controleKmService.buscarPorId(id));
        } catch (Exception e) {
            throw new ControleKmNaoEncontrado();
        }

    }

    /**
     * SALVA UM CONTROLE-KM
     *
     * @param controleKm
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_CONTROLE-KM') and #oauth2.hasScope('write')")
    public ResponseEntity<ControleKm> save(@Valid @RequestBody ControleKm controleKm, HttpServletResponse response) {
        ControleKm controleKmSalvo = controleKmService.salvar(controleKm);
        publisher.publishEvent(new EventResourceCreated(this, response, controleKmSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(controleKmSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param controleKm
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CONTROLE-KM') and #oauth2.hasScope('write')")
    public ResponseEntity<ControleKm> update(@Valid @PathVariable String key, @Valid @RequestBody ControleKm controleKm) {
        try {
            Long id = Long.parseLong(new Criptografia().decryptFromHex(key));
            return ResponseEntity.status(HttpStatus.OK).body(controleKmService.atualizar(id, controleKm));
        } catch (Exception e) {
            throw new ControleKmNaoEncontrado();
        }
    }

    /**
     * REMOVE O OBJETO PASSADO
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_CONTROLE-KM') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        //TODO: COLOCAR O EXCLUIR COMO UM RECURSO DO SERVICE, TIRAR A REGRA DE NEGOCIO DAQUI
        try {
            Long id = Long.parseLong(new Criptografia().decryptFromHex(key));
            ControleKm controleKm = controleKmService.buscarPorId(id);
            controleKmRepository.delete(controleKm.getId());
        } catch (Exception e) {
            throw new ControleKmNaoEncontrado();
        }

    }
}
