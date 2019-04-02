package br.com.wcorrea.transport.api.repository.combustivel;

import br.com.wcorrea.transport.api.model.Combustivel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CombustivelRepository extends JpaRepository<Combustivel, Long>, CombustivelRepositoryQuery {

}
