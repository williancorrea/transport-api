package br.com.wcorrea.transport.api.resource.veiculo;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.veiculo.VeiculoModelo;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.service.VeiculoModeloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/veiculosModelo")
public class VeiculoModeloResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private VeiculoModeloService veiculoModeloService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public List<VeiculoModelo> findAll(QueryFiltroPadrao filtro) {
        return veiculoModeloService.listar(filtro);
    }

    @GetMapping("/cmb")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<VeiculoModelo> listAllComboBox(QueryFiltroPadrao filtro, Pageable pageable) {
        return veiculoModeloService.listarSomenteAtivo(filtro);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public ResponseEntity<VeiculoModelo> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(veiculoModeloService.buscarPorId(veiculoModeloService.descriptografarKey(key)));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<VeiculoModelo> save(@Valid @RequestBody VeiculoModelo veiculoModelo, HttpServletResponse response) {
        VeiculoModelo veiculoSalvo = veiculoModeloService.salvar(veiculoModelo);
        publisher.publishEvent(new EventResourceCreated(this, response, veiculoSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<VeiculoModelo> update(@Valid @PathVariable String key, @Valid @RequestBody VeiculoModelo veiculoModelo) {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoModeloService.atualizar(veiculoModeloService.descriptografarKey(key), veiculoModelo));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_VEICULO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        veiculoModeloService.excluir(key);
    }
}
