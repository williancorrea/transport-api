package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaRepository;
import br.com.wcorrea.transport.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LIST_PERSON') and #oauth2.hasScope('read')")
    public Page<Pessoa> findAll(PessoaFiltro pessoaFiltro, Pageable paginavel) {
        return pessoaRepository.findAll(pessoaFiltro, paginavel);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LIST_PERSON') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> findOne(@Valid @PathVariable String key) {
        Pessoa pessoaEncontrada = pessoaService.buscarPorId(pessoaService.descriptografarKey(key));
        return pessoaEncontrada != null ? ResponseEntity.ok(pessoaEncontrada) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SAVE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaService.save(pessoa);
        publisher.publishEvent(new EventResourceCreated(this, response, pessoaSalva.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> update(@Valid @PathVariable String key, @Valid @RequestBody Pessoa Pessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.update(pessoaService.descriptografarKey(key), Pessoa));
    }

    @PutMapping("/{key}/motorista")
//    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> updateMotorista(@Valid @PathVariable String key, @Valid @RequestBody Pessoa Pessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.updateMotorista(pessoaService.descriptografarKey(key), Pessoa));
    }

    @GetMapping("/cmbClientes")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<Pessoa> listAllComboBoxClientes(PessoaFiltro filtro, Pageable pageable) {
        return pessoaService.pesquisaClienteFornecedorCmb(filtro, pageable);
    }

    @GetMapping("/cmbEmpresaRosinha")
    public List<Pessoa> listAllComboBoxEmpresaRosinha(PessoaFiltro filtro, Pageable pageable) {
        return pessoaService.pesquisaEmpresaRosinhaCmb(filtro, pageable);
    }

    @GetMapping("/cmbRepresentanteComercialEmpresaRosinha")
    public List<Pessoa> listAllComboBoxRepresentanteComercialEmpresaRosinha(PessoaFiltro filtro, Pageable pageable) {
        return pessoaService.pesquisaRepresentanteComercialEmpresaRosinhaCmb(filtro, pageable);
    }

    @GetMapping("/cmbMotoristas")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<Pessoa> listAllComboBoxMotoristas(PessoaFiltro filtro, Pageable pageable) {
        return pessoaService.pesquisaMotoristaCmb(filtro, pageable);
    }

    @GetMapping("/cmbClientes/cpf/{cpf}")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> buscarPorCPF(@Valid @PathVariable String cpf) {
        Pessoa p = pessoaService.buscarPorCPF(cpf);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cmbClientes/cnpj/{cnpj}")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> buscarPorCNPJ(@Valid @PathVariable String cnpj) {
        Pessoa p = pessoaService.buscarPorCNPJ(cnpj);
        return p != null ? ResponseEntity.ok(p) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{key}/motorista/ativar")
//    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> motoristaAtivar(@Valid @PathVariable String key) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.motoristaAtivar(key));
    }

    @PutMapping("/{key}/motorista/desativar")
//    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> motoristaDesativar(@Valid @PathVariable String key) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.motoristaDesativar(key));
    }

}
