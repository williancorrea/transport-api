package br.com.wcorrea.transport.api.repository.CentroDeCusto;

import br.com.wcorrea.transport.api.model.CentroDeCusto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long>, CentroDeCustoRepositoryQuery {

}