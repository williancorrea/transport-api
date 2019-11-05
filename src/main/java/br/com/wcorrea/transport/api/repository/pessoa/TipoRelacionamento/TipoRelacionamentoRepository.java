package br.com.wcorrea.transport.api.repository.pessoa.TipoRelacionamento;

import br.com.wcorrea.transport.api.model.pessoa.TipoRelacionamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoRelacionamentoRepository extends JpaRepository<TipoRelacionamento, Long>, TipoRelacionamentoRepositoryQuery {
}
