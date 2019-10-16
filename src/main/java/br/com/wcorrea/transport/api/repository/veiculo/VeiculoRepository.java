package br.com.wcorrea.transport.api.repository.veiculo;

import br.com.wcorrea.transport.api.model.veiculo.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long>, VeiculoRepositoryQuery {

    Veiculo findByPlaca(String placa);
}
