package br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacao;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacaoRepository;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacaoRepositoryFiltro;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping("financeiro/bancos-extrato-abreviacao")
public class BancoExtratoAbreviacaoResource {

    @Autowired
    private BancoExtratoAbreviacaoRepository bancoExtratoAbreviacaoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private BancoExtratoAbreviacaoService bancoExtratoAbreviacaoService;

    @GetMapping
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public Page<BancoExtratoAbreviacao> findAll(BancoExtratoAbreviacaoRepositoryFiltro bancoExtratoAbreviacaoRepositoryFiltro, Pageable pageable) {
        return bancoExtratoAbreviacaoRepository.findAll(bancoExtratoAbreviacaoRepositoryFiltro, pageable);
    }

    @GetMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_LISTAR_BANCO') and #oauth2.hasScope('read')")
    public ResponseEntity<BancoExtratoAbreviacao> findOne(@Valid @PathVariable String key) {
        BancoExtratoAbreviacao bancoExtratoAbreviacaoEncontrado = bancoExtratoAbreviacaoService.buscarPorId(bancoExtratoAbreviacaoService.buscarPorKey(key));
        return bancoExtratoAbreviacaoEncontrado != null ? ResponseEntity.ok(bancoExtratoAbreviacaoEncontrado) : ResponseEntity.notFound().build();
    }

    @PostMapping
//    @PreAuthorize("hasAuthority('ROLE_SALVAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<BancoExtratoAbreviacao> save(@Valid @RequestBody BancoExtratoAbreviacao bancoExtratoAbreviacao, HttpServletResponse response) throws Exception {
        bancoExtratoAbreviacao = bancoExtratoAbreviacaoService.salvar(bancoExtratoAbreviacao);
        publisher.publishEvent(new EventResourceCreated(this, response, bancoExtratoAbreviacao.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(bancoExtratoAbreviacao);
    }

    @PutMapping("/{key}")
//    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_BANCO') and #oauth2.hasScope('write')")
    public ResponseEntity<BancoExtratoAbreviacao> update(@Valid @PathVariable String key, @Valid @RequestBody BancoExtratoAbreviacao bancoExtratoAbreviacao) {
        return ResponseEntity.status(HttpStatus.OK).body(bancoExtratoAbreviacaoService.atualizar(bancoExtratoAbreviacaoService.buscarPorKey(key), bancoExtratoAbreviacao));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
//    @PreAuthorize("hasAuthority('ROLE_DELETAR_BANCO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        bancoExtratoAbreviacaoRepository.deleteById(bancoExtratoAbreviacaoService.buscarPorKey(key));
    }
}
