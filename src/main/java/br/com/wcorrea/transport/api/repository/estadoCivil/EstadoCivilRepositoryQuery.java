package br.com.wcorrea.transport.api.repository.estadoCivil;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface EstadoCivilRepositoryQuery {
    Page<EstadoCivil> findAll(EstadoCivilFiltro filtro, Pageable paginacao);
}
