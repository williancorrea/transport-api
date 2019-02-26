package br.com.wcorrea.transport.api.repository.UnidadeMedida;

import br.com.wcorrea.transport.api.model.UnidadeMedida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long>, UnidadeMedidaRepositoryQuery {
}
