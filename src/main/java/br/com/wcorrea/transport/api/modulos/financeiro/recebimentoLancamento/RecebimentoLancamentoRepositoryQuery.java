package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecebimentoLancamentoRepositoryQuery {
    Page<RecebimentoLancamento> findAll(RecebimentoLancamentoRepositoryFiltro filtro, Pageable pageable);
}
