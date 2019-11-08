package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecebimentoParcelaStatusRepositoryQuery {
    Page<RecebimentoParcelaStatus> findAll(RecebimentoParcelaStatusRepositoryFiltro filtro, Pageable pageable);
}
