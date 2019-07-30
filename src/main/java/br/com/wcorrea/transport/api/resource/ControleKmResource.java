package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.ControleKm;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmFiltro;
import br.com.wcorrea.transport.api.repository.controleKm.ControleKmRepository;
import br.com.wcorrea.transport.api.service.ControleKmService;
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

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CONTROLE-KM') and #oauth2.hasScope('read')")
    public Page<ControleKm> findAll(ControleKmFiltro filtro, Pageable paginacao) {
        return controleKmRepository.findAll(filtro, paginacao);
    }

    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CONTROLE-KM') and #oauth2.hasScope('read')")
    public ResponseEntity<ControleKm> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(controleKmService.buscarPorId(controleKmService.buscarPorKey(key)));
    }

    /**
     * Busca o km minimo a ser informado no periodo
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
     */
    @GetMapping("/kmMaximoPeriodo")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CONTROLE-KM') and #oauth2.hasScope('read')")
    public ResponseEntity<Long> buscarKmChegadaMaximoPorPeriodo(
            @RequestParam String veiculoId,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Date dataChegada) {
        return ResponseEntity.ok(controleKmService.buscarKmMaximoPeloPeriodo(dataChegada, controleKmService.buscarPorKey(veiculoId)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_CONTROLE-KM') and #oauth2.hasScope('write')")
    public ResponseEntity<ControleKm> save(@Valid @RequestBody ControleKm controleKm, HttpServletResponse response) {
        ControleKm controleKmSalvo = controleKmService.salvar(controleKm);
        publisher.publishEvent(new EventResourceCreated(this, response, controleKmSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(controleKmSalvo);
    }

    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CONTROLE-KM') and #oauth2.hasScope('write')")
    public ResponseEntity<ControleKm> update(@Valid @PathVariable String key, @Valid @RequestBody ControleKm controleKm) {
        return ResponseEntity.status(HttpStatus.OK).body(controleKmService.atualizar(controleKmService.buscarPorKey(key), controleKm));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_CONTROLE-KM') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        ControleKm controleKm = controleKmService.buscarPorId(controleKmService.buscarPorKey(key));
        controleKmRepository.deleteById(controleKm.getId());
    }
}
