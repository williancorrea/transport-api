package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecebimentoParcelaRepositoryQuery {
    Page<RecebimentoParcela> findAll(RecebimentoParcelaRepositoryFiltro filtro, Pageable pageable);
}
