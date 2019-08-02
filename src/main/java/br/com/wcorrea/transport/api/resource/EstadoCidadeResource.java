package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.model.pessoa.Estado;
import br.com.wcorrea.transport.api.repository.EstadoRepository;
import br.com.wcorrea.transport.api.service.EstadoCidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoCidadeResource {

    @Autowired
    private EstadoCidadeService estadoCidadeService;

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping
    @PreAuthorize("hasAuthority('ROLE_LISTAR_ESTADO') and #oauth2.hasScope('read')")
    public List<Estado> listarTodosEstados() {
        return estadoRepository.listarEstadorPorDescricao();
    }

    @GetMapping("/{key}")
    @PreAuthorize("hasAuthority('ROLE_LISTAR_CIDADE') and #oauth2.hasScope('read')")
    public List<Cidade> listarTodasCidadesPorEstado(@Valid @PathVariable String key) {
        return estadoCidadeService.buscarCidadePorEstado(estadoCidadeService.buscarEstadoPorKey(key));
    }
}
