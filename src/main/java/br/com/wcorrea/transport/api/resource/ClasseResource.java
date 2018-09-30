package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Classe;
import br.com.wcorrea.transport.api.repository.classe.ClasseFiltro;
import br.com.wcorrea.transport.api.repository.classe.ClasseRepository;
import br.com.wcorrea.transport.api.service.ClasseService;
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
@RequestMapping("/classes")
public class ClasseResource {

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private ClasseService classeService;

    /**
     * RECUPERA A LISTA DE REGISTRO DE CLASSE
     *
     * @return
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CLASSE') and #oauth2.hasScope('read')")
    public Page<Classe> findAll(ClasseFiltro filtro, Pageable paginacao) {
        return classeRepository.findAll(filtro, paginacao);
    }

    /**
     * RECUPERA UM CLASSE ESPECIFICO
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CLASSE') and #oauth2.hasScope('read')")
    public ResponseEntity<Classe> findOne(@Valid @PathVariable String key) {
        return ResponseEntity.ok(classeService.buscarPorId(new Criptografia().getKey(key)));
    }

    /**
     * SALVA UM CLASSE
     *
     * @param classe
     * @param response
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SALVAR_CLASSE') and #oauth2.hasScope('write')")
    public ResponseEntity<Classe> save(@Valid @RequestBody Classe classe, HttpServletResponse response) {
        Classe classeSalvo = classeService.salvar(classe);
        publisher.publishEvent(new EventResourceCreated(this, response, classeSalvo.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(classeSalvo);
    }

    /**
     * ATUALIZA O OBJETO PASSADO POR PARAMETRO
     *
     * @param classe
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_ATUALIZAR_CLASSE') and #oauth2.hasScope('write')")
    public ResponseEntity<Classe> update(@Valid @PathVariable String key, @Valid @RequestBody Classe classe) {
        return ResponseEntity.status(HttpStatus.OK).body(classeService.atualizar(new Criptografia().getKey(key), classe));
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
        Classe classe = classeService.buscarPorId(new Criptografia().getKey(key));
        classeRepository.delete(classe.getId());
    }
}
