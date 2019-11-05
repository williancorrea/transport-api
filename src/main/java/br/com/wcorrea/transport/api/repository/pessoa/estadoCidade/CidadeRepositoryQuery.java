package br.com.wcorrea.transport.api.repository.pessoa.estadoCidade;

import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CidadeRepositoryQuery {
    Page<Cidade> findAll(QueryFiltroPadrao filtro, Pageable pageable);
}
