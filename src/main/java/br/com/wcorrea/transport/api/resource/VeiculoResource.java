package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.veiculo.Veiculo;
import br.com.wcorrea.transport.api.model.veiculo.VeiculoResumo;
import br.com.wcorrea.transport.api.repository.veiculo.VeiculoFiltro;
import br.com.wcorrea.transport.api.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/veiculos")
public class VeiculoResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private VeiculoService veiculoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public Page<Veiculo> findAll(VeiculoFiltro filtro, Pageable paginacao) {
        return veiculoService.listar(filtro, paginacao);
    }

    @GetMapping("/cmb")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<VeiculoResumo> listAllComboBox(VeiculoFiltro filtro, Pageable pageable) {
        filtro.setSomenteAtivo(true);
        Page<Veiculo> page = veiculoService.listar(filtro, pageable);
        List<VeiculoResumo> lista = new ArrayList<>();
        for (Veiculo c : page.getContent()) {
            lista.add(new VeiculoResumo(c));
        }
        return lista;
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_VEICULO') and #oauth2.hasScope('read')")
    public ResponseEntity<Veiculo> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(veiculoService.buscarPorId(veiculoService.descriptografarKey(key)));
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<Veiculo> save(@Valid @RequestBody Veiculo veiculo, HttpServletResponse response) {
        Veiculo veiculoSalvo = veiculoService.salvar(veiculo);
        publisher.publishEvent(new EventResourceCreated(this, response, veiculoSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculoSalvo);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_VEICULO') and #oauth2.hasScope('write')")
    public ResponseEntity<Veiculo> update(@Valid @PathVariable String key, @Valid @RequestBody Veiculo veiculo) {
        return ResponseEntity.status(HttpStatus.OK).body(veiculoService.atualizar(veiculoService.descriptografarKey(key), veiculo));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_VEICULO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        veiculoService.excluir(key);
    }
}
