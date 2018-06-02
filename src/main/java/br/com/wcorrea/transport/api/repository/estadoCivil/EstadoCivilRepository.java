package br.com.wcorrea.transport.api.repository.estadoCivil;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long>, EstadoCivilRepositoryQuery {
}
