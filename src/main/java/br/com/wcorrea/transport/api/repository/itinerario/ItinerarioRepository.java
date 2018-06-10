package br.com.wcorrea.transport.api.repository.itinerario;

import br.com.wcorrea.transport.api.model.Itinerario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItinerarioRepository extends JpaRepository<Itinerario, Long>, ItinerarioRepositoryQuery {
}
