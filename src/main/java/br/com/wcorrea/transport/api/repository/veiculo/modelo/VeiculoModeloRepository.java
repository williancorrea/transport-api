package br.com.wcorrea.transport.api.repository.veiculo.modelo;

import br.com.wcorrea.transport.api.model.veiculo.VeiculoModelo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoModeloRepository extends JpaRepository<VeiculoModelo, Long> {
    List<VeiculoModelo> findByNomeContainingIgnoreCase(String nome);

    List<VeiculoModelo> findByNomeIsNullOrNomeContainingIgnoreCaseAndInativoFalseOrderByNome(String nome);
}
