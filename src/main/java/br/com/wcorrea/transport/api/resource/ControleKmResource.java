package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmFiltro;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmRepository;
import br.com.wcorrea.transport.api.service.ControleKmService;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.Date;

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
        return ResponseEntity.ok(controleKmService.buscarPorId(controleKmService.buscarPorKey(key)));
    }

    /**
     * Busca o km minimo a ser informado no periodo
     *
     * @param veiculoId
     * @param dataSaida
     * @return
     */
    @GetMapping("/kmMinimoPeriodo")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CONTROLE-KM') and #oauth2.hasScope('read')")
    public ResponseEntity<Long> buscarKmSaidaMinimoPorPeriodo(
            @RequestParam String veiculoId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dataSaida) {
        return ResponseEntity.ok(controleKmService.buscarKmMinimoPeloPeriodo(dataSaida, controleKmService.buscarPorKey(veiculoId)));
    }

    /**
     * Busca o Km máximo a ser informado no periodo
     * @param veiculoId
     * @param dataChegada
     * @return
     */
    @GetMapping("/kmMaximoPeriodo")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CONTROLE-KM') and #oauth2.hasScope('read')")
    public ResponseEntity<Long> buscarKmChegadaMaximoPorPeriodo(
            @RequestParam String veiculoId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dataChegada) {
        return ResponseEntity.ok(controleKmService.buscarKmMaximoPeloPeriodo(dataChegada, controleKmService.buscarPorKey(veiculoId)));
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
        return ResponseEntity.status(HttpStatus.OK).body(controleKmService.atualizar(controleKmService.buscarPorKey(key), controleKm));
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
        ControleKm controleKm = controleKmService.buscarPorId(controleKmService.buscarPorKey(key));
        controleKmRepository.delete(controleKm.getId());
    }
}
