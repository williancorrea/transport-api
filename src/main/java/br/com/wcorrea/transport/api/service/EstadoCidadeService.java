package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.repository.estadoCidade.CidadeRepository;
import br.com.wcorrea.transport.api.service.exception.CidadeNaoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoCidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> buscarCidades(QueryFiltroPadrao filtro, Pageable page) {
        return cidadeRepository.findAll(filtro, page).getContent();
    }

    public Cidade buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<Cidade> c = cidadeRepository.findById(id);
            if (c.isPresent()) {
                return c.get();
            }
        }
        throw new CidadeNaoEncontrada();
    }

}
