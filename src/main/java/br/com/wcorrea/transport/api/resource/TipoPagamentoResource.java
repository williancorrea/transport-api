package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.TipoPagamento;
import br.com.wcorrea.transport.api.repository.tipoPagamento.TipoPagamentoFiltro;
import br.com.wcorrea.transport.api.repository.tipoPagamento.TipoPagamentoRepository;
import br.com.wcorrea.transport.api.service.TipoPagamentoService;
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
import java.util.List;

@RestController
@RequestMapping("/tipo_pagamentos")
public class TipoPagamentoResource {

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private TipoPagamentoService tipoPagamentoService;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-PAGAMENTO') and #oauth2.hasScope('read')")
    public Page<TipoPagamento> findAll(TipoPagamentoFiltro filtro, Pageable paginacao) {
        return tipoPagamentoRepository.findAll(filtro, paginacao);
    }

    @GetMapping("/ativos")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-PAGAMENTO') and #oauth2.hasScope('read')")
    public List<TipoPagamento> listarTudoAtivo() {
        return tipoPagamentoRepository.listarTudoAtivo();
    }

    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-PAGAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<TipoPagamento> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(tipoPagamentoService.buscarPorId(tipoPagamentoService.buscarPorKey(key)));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_TIPO-PAGAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoPagamento> save(@Valid @RequestBody TipoPagamento tipoPagamento, HttpServletResponse response) {
        TipoPagamento tipoPagamentoSalvo = tipoPagamentoService.salvar(tipoPagamento);
        publisher.publishEvent(new EventResourceCreated(this, response, tipoPagamentoSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(tipoPagamentoSalvo);
    }

    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TIPO-PAGAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<TipoPagamento> update(@Valid @PathVariable String key, @Valid @RequestBody TipoPagamento tipoPagamento) {
        return ResponseEntity.status(HttpStatus.OK).body(tipoPagamentoService.atualizar(tipoPagamentoService.buscarPorKey(key), tipoPagamento));
    }

    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_TIPO-PAGAMENTO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        TipoPagamento tipoPagamento = tipoPagamentoService.buscarPorId(tipoPagamentoService.buscarPorKey(key));
        tipoPagamentoRepository.deleteById(tipoPagamento.getId());
    }
}
