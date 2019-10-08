package br.com.wcorrea.transport.api.resource;

import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping("/cmbCidades")
    public List<Cidade> listAllComboBoxCidades(QueryFiltroPadrao filtro, Pageable pageable) {
        filtro.setSomenteAtivo(true);
        return cidadeService.buscarCidades(filtro, pageable);
    }
}
