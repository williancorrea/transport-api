package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.model.Fretamento;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.service.FretamentoService;
import br.com.wcorrea.transport.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/fretamentos")
public class FretamentoResource {

    //    @Autowired
//    private BancoRepository bancoRepository;
//
//    @Autowired
//    private ApplicationEventPublisher publisher;
//
    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private FretamentoService fretamentoService;

    //
//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_FRETAMENTO') and #oauth2.hasScope('read')")
//    public Page<Banco> findAll(BancoFiltro bancoFiltro, Pageable pageable) {
//        return bancoRepository.findAll(bancoFiltro, pageable);
//    }
//
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_FRETAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<Fretamento> findOne(@Valid @PathVariable String key) {
//        Pessoa obj = pessoaService.findOne(pessoaService.buscarPorKey(key));

        Fretamento fretamento = new Fretamento();
        fretamento.setId(1l);
        //TODO: Remover mascara do campo
        fretamento.setCliente(pessoaService.findOneByCPF("330.669.088-01"));

//        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
        return ResponseEntity.ok(fretamento);
    }

    @GetMapping("/cmbCliente")
    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<Pessoa> listAllComboBox(PessoaFiltro filtro, Pageable pageable) {
        filtro.setSomenteAtivo(true);

        return pessoaService.pesquisaClienteFornecedorCmb(filtro, pageable);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_FRETAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<Fretamento> save(@Valid @RequestBody Fretamento fretamento, HttpServletResponse response) throws Exception {

        fretamento = fretamentoService.salvar(fretamento);

//        fretamento = bancoRepository.saveAndFlush(fretamento);
//        publisher.publishEvent(new EventResourceCreated(this, response, fretamento.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(fretamento);
    }
//
//    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_FRETAMENTO') and #oauth2.hasScope('write')")
//    public ResponseEntity<Banco> update(@Valid @PathVariable String key, @Valid @RequestBody Banco banco) {
//        return ResponseEntity.status(HttpStatus.OK).body(bancoService.update(bancoService.buscarPorKey(key), banco));
//    }
//
//    @DeleteMapping("/{key}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_FRETAMENTO') and #oauth2.hasScope('write')")
//    public void delete(@PathVariable String key) {
//        bancoRepository.delete(bancoService.buscarPorKey(key));
//    }
}
