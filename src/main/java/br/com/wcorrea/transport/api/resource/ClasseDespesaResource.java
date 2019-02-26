package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.ClasseDespesa;
import br.com.wcorrea.transport.api.repository.classeDespesa.ClasseDespesaRepository;
import br.com.wcorrea.transport.api.repository.classeDespesa.ClasseDespesaFiltro;
import br.com.wcorrea.transport.api.service.ClasseDespesaService;
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
@RequestMapping("/classes_despesas")
public class ClasseDespesaResource {

    @Autowired
    private ClasseDespesaRepository classeDespesaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ClasseDespesaService classeDespesaService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE CLASSE-DESPESA
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CLASSE-DESPESA') and #oauth2.hasScope('read')")
    public Page<ClasseDespesa> findAll(ClasseDespesaFiltro filtro, Pageable paginacao) {
        return classeDespesaRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM CLASSE-DESPESA ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CLASSE-DESPESA') and #oauth2.hasScope('read')")
    public ResponseEntity<ClasseDespesa> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(classeDespesaService.buscarPorId(classeDespesaService.buscarPorKey(key)));
    }

    /**
     * SALVA UM CLASSE-DESPESA
     *
     * @param classeDespesa
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_CLASSE-DESPESA') and #oauth2.hasScope('write')")
    public ResponseEntity<ClasseDespesa> save(@Valid @RequestBody ClasseDespesa classeDespesa, HttpServletResponse response) {
        ClasseDespesa classeDespesaSalvo = classeDespesaService.salvar(classeDespesa);
        publisher.publishEvent(new EventResourceCreated(this, response, classeDespesaSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(classeDespesaSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param classeDespesa
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CLASSE-DESPESA') and #oauth2.hasScope('write')")
    public ResponseEntity<ClasseDespesa> update(@Valid @PathVariable String key, @Valid @RequestBody ClasseDespesa classeDespesa) {
        return ResponseEntity.status(HttpStatus.OK).body(classeDespesaService.atualizar(classeDespesaService.buscarPorKey(key), classeDespesa));
    }

    /**
     * REMOVE O OBJETO PASSADO
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_CLASSE-DESPESA') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        ClasseDespesa classeDespesa = classeDespesaService.buscarPorId(classeDespesaService.buscarPorKey(key));
        classeDespesaRepository.delete(classeDespesa.getId());
    }
}
