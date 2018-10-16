package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.model.ConfiguracaoSistema;
import br.com.wcorrea.transport.api.repository.ConfiguracaoSistemaRepository;
import br.com.wcorrea.transport.api.service.ConfiguracaoSistemaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/config")
public class ConfiguracaoSistemaResource {

    @Autowired
    private ConfiguracaoSistemaRepository configuracaoSistemaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ConfiguracaoSistemaService configuracaoSistemaService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE TIPO-PAGAMENTO
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-PAGAMENTO') and #oauth2.hasScope('read')")
    public List<ConfiguracaoSistema> findAll() {
        return configuracaoSistemaRepository.findAll();
    }

    /**
     * RECUPERA UM TIPO-PAGAMENTO ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_TIPO-PAGAMENTO') and #oauth2.hasScope('read')")
    public ResponseEntity<ConfiguracaoSistema> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(configuracaoSistemaService.buscarPorId(configuracaoSistemaService.buscarPorKey(key)));
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param configuracaoSistema
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_TIPO-PAGAMENTO') and #oauth2.hasScope('write')")
    public ResponseEntity<ConfiguracaoSistema> update(@Valid @PathVariable String key, @Valid @RequestBody ConfiguracaoSistema configuracaoSistema) {
        return ResponseEntity.status(HttpStatus.OK).body(configuracaoSistemaService.atualizar(configuracaoSistemaService.buscarPorKey(key), configuracaoSistema));
    }
}
