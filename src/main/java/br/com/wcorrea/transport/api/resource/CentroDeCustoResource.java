package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.CentroDeCusto;
import br.com.wcorrea.transport.api.repository.CentroDeCusto.CentroDeCustoFiltro;
import br.com.wcorrea.transport.api.repository.CentroDeCusto.CentroDeCustoRepository;
import br.com.wcorrea.transport.api.service.CentroDeCustoService;
import br.com.wcorrea.transport.api.utils.Criptografia;
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

@RestController
@RequestMapping("/centroDeCusto")
public class CentroDeCustoResource {

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private CentroDeCustoService centroDeCustoService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE CENTRO_DE_CUSTO
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CENTRO_DE_CUSTO') and #oauth2.hasScope('read')")
    public Page<CentroDeCusto> findAll(CentroDeCustoFiltro filtro, Pageable paginacao) {
        return centroDeCustoRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM CENTRO_DE_CUSTO ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CENTRO_DE_CUSTO') and #oauth2.hasScope('read')")
    public ResponseEntity<CentroDeCusto> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(centroDeCustoService.buscarPorId(centroDeCustoService.buscarPorKey(key)));
    }

    /**
     * SALVA UM CENTRO_DE_CUSTO
     *
     * @param centroDeCusto
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_CENTRO_DE_CUSTO') and #oauth2.hasScope('write')")
    public ResponseEntity<CentroDeCusto> save(@Valid @RequestBody CentroDeCusto centroDeCusto, HttpServletResponse response) {
        CentroDeCusto centroDeCustoSalvo = centroDeCustoService.salvar(centroDeCusto);
        publisher.publishEvent(new EventResourceCreated(this, response, centroDeCustoSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(centroDeCustoSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param centroDeCusto
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CENTRO_DE_CUSTO') and #oauth2.hasScope('write')")
    public ResponseEntity<CentroDeCusto> update(@Valid @PathVariable String key, @Valid @RequestBody CentroDeCusto centroDeCusto) {
        return ResponseEntity.status(HttpStatus.OK).body(centroDeCustoService.atualizar(centroDeCustoService.buscarPorKey(key), centroDeCusto));
    }

    /**
     * REMOVE O OBJETO PASSADO
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_CENTRO_DE_CUSTO') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        CentroDeCusto centroDeCusto = centroDeCustoService.buscarPorId(centroDeCustoService.buscarPorKey(key));
        centroDeCustoRepository.delete(centroDeCusto.getId());
    }
}
