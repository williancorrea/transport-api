package br.com.wcorrea.transport.api.repository.fretamento;

import br.com.wcorrea.transport.api.model.Fretamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FretamentoRepository extends JpaRepository<Fretamento, Long>, FretamentoRepositoryQuery {

}
