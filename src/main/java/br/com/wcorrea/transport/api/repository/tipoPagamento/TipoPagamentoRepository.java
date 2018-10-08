package br.com.wcorrea.transport.api.repository.tipoPagamento;

import br.com.wcorrea.transport.api.model.TipoPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TipoPagamentoRepository extends JpaRepository<TipoPagamento, Long>, TipoPagamentoRepositoryQuery {

}