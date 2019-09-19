package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.FretamentoEventual;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.repository.fretamentoEventual.FretamentoEventualFiltro;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.service.EstadoCidadeService;
import br.com.wcorrea.transport.api.service.FretamentoEventualService;
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
@RequestMapping("/fretamentosEventuais")
public class FretamentoEventualResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private FretamentoEventualService fretamentoEventualService;

    @Autowired
    private EstadoCidadeService estadoCidadeService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_ESTADO_CIVIL') and #oauth2.hasScope('read')")
    public Page<FretamentoEventual> findAll(FretamentoEventualFiltro filtro, Pageable paginacao) {
        return fretamentoEventualService.listarTodos(filtro, paginacao);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_FRETAMENTO_EVENTUAL') and #oauth2.hasScope('read')")
    public ResponseEntity<FretamentoEventual> buscarporKey(@Valid @PathVariable String key) {
        FretamentoEventual fretamentoEventualEncontrado = fretamentoEventualService.findOne(fretamentoEventualService.buscarPorKey(key));
        return fretamentoEventualEncontrado != null ? ResponseEntity.ok(fretamentoEventualEncontrado) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cmbClientes")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<Pessoa> listAllComboBoxClientes(PessoaFiltro filtro, Pageable pageable) {
        return pessoaService.pesquisaClienteFornecedorCmb(filtro, pageable);
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

    @GetMapping("/cmbCidades")
    public List<Cidade> listAllComboBoxCidades(QueryFiltroPadrao filtro, Pageable pageable) {
        filtro.setSomenteAtivo(true);
        return estadoCidadeService.buscarCidades(filtro, pageable);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_FRETAMENTO_EVENTUAL') and #oauth2.hasScope('write')")
    public ResponseEntity<FretamentoEventual> salvar(@Valid @RequestBody FretamentoEventual fretamentoEventual, HttpServletResponse response) throws Exception {
        fretamentoEventual = fretamentoEventualService.salvar(fretamentoEventual);
        publisher.publishEvent(new EventResourceCreated(this, response, fretamentoEventual.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fretamentoEventual);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_FRETAMENTO_EVENTUAL') and #oauth2.hasScope('write')")
    public ResponseEntity<FretamentoEventual> atualizar(@Valid @PathVariable String key, @Valid @RequestBody FretamentoEventual fretamentoEventual) {
        return ResponseEntity.status(HttpStatus.OK).body(fretamentoEventualService.atualizar(fretamentoEventualService.buscarPorKey(key), fretamentoEventual));
    }

//    @DeleteMapping("/{key}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_FRETAMENTO_EVENTUAL') and #oauth2.hasScope('write')")
//    public void delete(@PathVariable String key) {
//        bancoRepository.delete(bancoService.buscarPorKey(key));
//    }
}
