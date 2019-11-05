package br.com.wcorrea.transport.api.repository.pessoa.estadoCivil;

import br.com.wcorrea.transport.api.model.pessoa.EstadoCivil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EstadoCivilRepositoryQuery {
    Page<EstadoCivil> findAll(EstadoCivilFiltro filtro, Pageable paginacao);
}
