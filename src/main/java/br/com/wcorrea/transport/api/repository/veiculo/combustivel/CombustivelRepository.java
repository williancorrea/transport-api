package br.com.wcorrea.transport.api.repository.veiculo.combustivel;

import br.com.wcorrea.transport.api.model.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombustivelRepository extends JpaRepository<Combustivel, Long>, CombustivelRepositoryQuery {

}
