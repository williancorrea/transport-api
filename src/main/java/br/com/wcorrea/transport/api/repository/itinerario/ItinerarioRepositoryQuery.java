package br.com.wcorrea.transport.api.repository.itinerario;

import br.com.wcorrea.transport.api.model.Itinerario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ItinerarioRepositoryQuery {
    Page<Itinerario> findAll(ItinerarioFiltro filtro, Pageable paginacao);
}
