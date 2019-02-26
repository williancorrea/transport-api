package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.hateoas.EventResourceCreated;
import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaRepository;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.service.PessoaService;
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

/**
 * Class responsible for providing all the resources needed to handle persons
 */
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    /**
     * RECUPERA A LISTA DE PESSOAS, DE FORMA PAGINADA
     *
     * @return List of persons
     */
    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LIST_PERSON') and #oauth2.hasScope('read')")
    public Page<Pessoa> findAll(PessoaFiltro pessoaFiltro, Pageable paginavel) {
        return pessoaRepository.findAll(pessoaFiltro, paginavel);
    }

    /**
     * RECUPERA UMA PESSOA ESPECIFICA
     *
     * @return
     */
    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LIST_PERSON') and #oauth2.hasScope('read')")
    public ResponseEntity<Pessoa> findOne(@Valid @PathVariable String key) {
        Pessoa pessoaEncontrada = pessoaService.buscarPorId(pessoaService.buscarPorKey(key));
        return pessoaEncontrada != null ? ResponseEntity.ok(pessoaEncontrada) : ResponseEntity.notFound().build();
    }

    /**
     * SALVA OS DADOS DE UMA PESSOA
     *
     * @param pessoa   pessoa
     * @param response HttpServletResponse
     * @return
     */
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_SAVE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> save(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {
        Pessoa pessoaSalva = pessoaService.save(pessoa);
        publisher.publishEvent(new EventResourceCreated(this, response, pessoaSalva.getKey()));
        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    /**
     * ATUALIZA OS DADOS DE UMA PESSOA
     *
     * @param Pessoa Pessoa
     * @return
     */
    @PutMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_UPDATE_PERSON') and #oauth2.hasScope('write')")
    public ResponseEntity<Pessoa> update(@Valid @PathVariable String key, @Valid @RequestBody Pessoa Pessoa) {
        return ResponseEntity.status(HttpStatus.OK).body(pessoaService.update(pessoaService.buscarPorKey(key), Pessoa));
    }

    /**
     * DELETA UMA PESSOA
     *
     * @return
     */
    @DeleteMapping("/{key}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('ROLE_DELETE_PERSON') and #oauth2.hasScope('write')")
    public void delete(@PathVariable String key) {
        pessoaRepository.delete(pessoaService.buscarPorKey(key));
    }
}
