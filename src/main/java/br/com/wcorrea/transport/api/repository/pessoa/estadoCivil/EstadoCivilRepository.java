package br.com.wcorrea.transport.api.repository.pessoa.estadoCivil;

import br.com.wcorrea.transport.api.model.pessoa.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Long>, EstadoCivilRepositoryQuery {
}
