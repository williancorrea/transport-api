package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Fretamento;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.service.FretamentoService;
import br.com.wcorrea.transport.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fretamentos")
public class FretamentoResource {

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private FretamentoService fretamentoService;

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_FRETAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Fretamento> findOne(@Valid @PathVariable String key) {
        Fretamento fretamentoEncontrado = fretamentoService.findOne(fretamentoService.buscarPorKey(key));
        return fretamentoEncontrado != null ? ResponseEntity.ok(fretamentoEncontrado) : ResponseEntity.notFound().build();
    }

    @GetMapping("/cmbCliente")
//    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<Pessoa> listAllComboBox(PessoaFiltro filtro, Pageable pageable) {
        filtro.setSomenteAtivo(true);
        return pessoaService.pesquisaClienteFornecedorCmb(filtro, pageable);
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_FRETAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Fretamento> save(@Valid @RequestBody Fretamento fretamento, HttpServletResponse response) throws Exception {
        fretamento = fretamentoService.salvar(fretamento);
        publisher.publishEvent(new EventResourceCreated(this, response, fretamento.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fretamento);
    }

    //
    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_FRETAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Fretamento> update(@Valid @PathVariable String key, @Valid @RequestBody Fretamento fretamento) {
        return ResponseEntity.status(HttpStatus.OK).body(fretamentoService.atualizar(fretamentoService.buscarPorKey(key), fretamento));
    }
//
//    @DeleteMapping("/{key}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_FRETAMENTO') and #oauth2.hasScope('write')")
//    public void delete(@PathVariable String key) {
//        bancoRepository.delete(bancoService.buscarPorKey(key));
//    }
}
