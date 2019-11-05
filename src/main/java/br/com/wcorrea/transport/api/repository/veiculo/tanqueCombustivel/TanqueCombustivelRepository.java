package br.com.wcorrea.transport.api.repository.veiculo.tanqueCombustivel;

import br.com.wcorrea.transport.api.model.TanqueCombustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TanqueCombustivelRepository extends JpaRepository<TanqueCombustivel, Long>, TanqueCombustivelRepositoryQuery {

}
