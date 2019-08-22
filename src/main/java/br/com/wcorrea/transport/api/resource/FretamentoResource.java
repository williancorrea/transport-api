package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.model.Fretamento;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    //
//    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
//    public Page<Banco> findAll(BancoFiltro bancoFiltro, Pageable pageable) {
//        return bancoRepository.findAll(bancoFiltro, pageable);
//    }
//
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<Fretamento> findOne(@Valid @PathVariable String key) {
//        Pessoa obj = pessoaService.findOne(pessoaService.buscarPorKey(key));

        Fretamento fretamento = new Fretamento();
        fretamento.setId(1l);
        //TODO: Remover mascara do campo
        fretamento.setPessoa(pessoaService.findOneByCPF("330.669.088-01"));

//        return bancoEncontrado != null ? ResponseEntity.ok(bancoEncontrado) : ResponseEntity.notFound().build();
        return ResponseEntity.ok(fretamento);
    }

    @GetMapping("/cmbCliente")
    @PreAuthorize("hasAuthority('ROLE_CMB-PADRAO') and #oauth2.hasScope('read')")
    public List<Pessoa> listAllComboBox(PessoaFiltro filtro, Pageable pageable) {
        filtro.setSomenteAtivo(true);

        return pessoaService.pesquisaClienteFornecedorCmb(filtro, pageable);
    }
//
//    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
//    public ResponseEntity<Banco> save(@Valid @RequestBody Banco banco, HttpServletResponse response) throws Exception {
//        banco = bancoRepository.saveAndFlush(banco);
//        publisher.publishEvent(new EventResourceCreated(this, response, banco.getKey()));
//        return ResponseEntity.status(HttpStatus.CREATED).body(banco);
//    }
//
//    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
//    public ResponseEntity<Banco> update(@Valid @PathVariable String key, @Valid @RequestBody Banco banco) {
//        return ResponseEntity.status(HttpStatus.OK).body(bancoService.update(bancoService.buscarPorKey(key), banco));
//    }
//
//    @DeleteMapping("/{key}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
//    public void delete(@PathVariable String key) {
//        bancoRepository.delete(bancoService.buscarPorKey(key));
//    }
}
