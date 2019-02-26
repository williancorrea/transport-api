package br.com.wcorrea.transport.api.repository.TipoRelacionamento;

import br.com.wcorrea.transport.api.model.TipoRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRelacionamentoRepository extends JpaRepository<TipoRelacionamento, Long>, TipoRelacionamentoRepositoryQuery {
}
