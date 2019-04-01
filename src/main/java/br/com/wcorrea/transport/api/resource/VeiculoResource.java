package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.repository.veiculo.VeiculoFiltro;
import br.com.wcorrea.transport.api.repository.veiculo.VeiculoRepository;
import br.com.wcorrea.transport.api.service.VeiculoService;
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
@RequestMapping("/veiculo")
public class VeiculoResource {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private VeiculoService veiculoService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE VEICULO
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public Page<Veiculo> findAll(VeiculoFiltro filtro, Pageable paginacao) {
        return veiculoRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM VEICULO ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public ResponseEntity<Veiculo> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(veiculoService.buscarPorId(veiculoService.buscarPorKey(key)));
    }

    /**
     * SALVA UM VEICULO
     *
     * @param veiculo
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<Veiculo> save(@Valid @RequestBody Veiculo veiculo, HttpServletResponse response) {
        Veiculo veiculoSalvo = veiculoService.salvar(veiculo);
        publisher.publishEvent(new EventResourceCreated(this, response, veiculoSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param veiculo
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<Veiculo> update(@Valid @PathVariable String key, @Valid @RequestBody Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoService.atualizar(veiculoService.buscarPorKey(key), veiculo));
    }

    /**
     * REMOVE O OBJETO PASSADO
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_VEICULO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        Veiculo veiculo = veiculoService.buscarPorId(veiculoService.buscarPorKey(key));
        veiculoRepository.delete(veiculo.getId());
    }
}
