package br.com.wcorrea.transport.api.repository.tipoPagamento;

import br.com.wcorrea.transport.api.model.TipoPagamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TipoPagamentoRepositoryQuery {
    Page<TipoPagamento> findAll(TipoPagamentoFiltro tipoPagamentoFiltro, Pageable pageable);
}
