package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoTipo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface RecebimentoTipoRepositoryQuery {
    Page<RecebimentoTipo> findAll(RecebimentoTipoRepositoryFiltro filtro, Pageable pageable);
}
