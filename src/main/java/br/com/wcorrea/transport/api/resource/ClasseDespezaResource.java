package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.ClasseDespeza;
import br.com.wcorrea.transport.api.repository.classeDespeza.ClasseDespezaFiltro;
import br.com.wcorrea.transport.api.repository.classeDespeza.ClasseDespezaDespezaRepository;
import br.com.wcorrea.transport.api.service.ClasseDespezaService;
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
@RequestMapping("/classes_despezas")
public class ClasseDespezaResource {

    @Autowired
    private ClasseDespezaDespezaRepository classeDespezaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ClasseDespezaService classeDespezaService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE CLASSE
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CLASSE') and #oauth2.hasScope('read')")
    public Page<ClasseDespeza> findAll(ClasseDespezaFiltro filtro, Pageable paginacao) {
        return classeDespezaRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM CLASSE ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CLASSE') and #oauth2.hasScope('read')")
    public ResponseEntity<ClasseDespeza> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(classeDespezaService.buscarPorId(classeDespezaService.buscarPorKey(key)));
    }

    /**
     * SALVA UM CLASSE
     *
     * @param classeDespeza
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_CLASSE') and #oauth2.hasScope('write')")
    public ResponseEntity<ClasseDespeza> save(@Valid @RequestBody ClasseDespeza classeDespeza, HttpServletResponse response) {
        ClasseDespeza classeDespezaSalvo = classeDespezaService.salvar(classeDespeza);
        publisher.publishEvent(new EventResourceCreated(this, response, classeDespezaSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(classeDespezaSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param classeDespeza
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CLASSE') and #oauth2.hasScope('write')")
    public ResponseEntity<ClasseDespeza> update(@Valid @PathVariable String key, @Valid @RequestBody ClasseDespeza classeDespeza) {
        return ResponseEntity.status(HttpStatus.OK).body(classeDespezaService.atualizar(classeDespezaService.buscarPorKey(key), classeDespeza));
    }

    /**
     * REMOVE O OBJETO PASSADO
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETAR_CLASSE') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        ClasseDespeza classeDespeza = classeDespezaService.buscarPorId(classeDespezaService.buscarPorKey(key));
        classeDespezaRepository.delete(classeDespeza.getId());
    }
}
