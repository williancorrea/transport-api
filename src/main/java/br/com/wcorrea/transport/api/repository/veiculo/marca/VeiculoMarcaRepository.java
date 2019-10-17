package br.com.wcorrea.transport.api.repository.veiculo.marca;

import br.com.wcorrea.transport.api.model.veiculo.VeiculoMarca;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VeiculoMarcaRepository extends JpaRepository<VeiculoMarca, Long> {

    List<VeiculoMarca> findByNomeContainingIgnoreCase(String nome);

    List<VeiculoMarca> findByNomeIsNullOrNomeContainingIgnoreCaseAndInativoFalseOrderByNome(String nome);
}
