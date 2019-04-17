package br.com.wcorrea.transport.api.repository.pessoa.Colaborador;

import br.com.wcorrea.transport.api.model.Pessoa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ColaboradorRepositoryQuery {
    Page<Pessoa> findAll(ColaboradorFiltro colaboradorFiltro, Pageable pageable);
}
