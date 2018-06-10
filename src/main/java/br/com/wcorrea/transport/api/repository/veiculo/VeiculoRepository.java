package br.com.wcorrea.transport.api.repository.veiculo;

import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.repository.veiculo.VeiculoRepositoryQuery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoRepositoryQuery {
}
