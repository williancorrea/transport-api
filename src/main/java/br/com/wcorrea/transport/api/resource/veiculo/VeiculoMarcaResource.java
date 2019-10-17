package br.com.wcorrea.transport.api.resource.veiculo;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.veiculo.VeiculoMarca;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.service.VeiculoMarcaService;
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
@RequestMapping("/veiculosMarca")
public class VeiculoMarcaResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private VeiculoMarcaService veiculoMarcaService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public List<VeiculoMarca> findAll(QueryFiltroPadrao filtro) {
        return veiculoMarcaService.listar(filtro);
    }

    @GetMapping("/cmb")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<VeiculoMarca> listAllComboBox(QueryFiltroPadrao filtro, Pageable pageable) {
        return veiculoMarcaService.listarSomenteAtivo(filtro);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public ResponseEntity<VeiculoMarca> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(veiculoMarcaService.buscarPorId(veiculoMarcaService.descriptografarKey(key)));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<VeiculoMarca> save(@Valid @RequestBody VeiculoMarca veiculoMarca, HttpServletResponse response) {
        VeiculoMarca veiculoSalvo = veiculoMarcaService.salvar(veiculoMarca);
        publisher.publishEvent(new EventResourceCreated(this, response, veiculoSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<VeiculoMarca> update(@Valid @PathVariable String key, @Valid @RequestBody VeiculoMarca veiculoMarca) {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoMarcaService.atualizar(veiculoMarcaService.descriptografarKey(key), veiculoMarca));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_VEICULO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        veiculoMarcaService.excluir(key);
    }
}
