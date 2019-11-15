package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaDetalhe;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecebimentoParcelaDetalheRepositoryQuery {
    Page<RecebimentoParcelaDetalhe> findAll(RecebimentoParcelaDetalheRepositoryFiltro filtro, Pageable pageable);
}
