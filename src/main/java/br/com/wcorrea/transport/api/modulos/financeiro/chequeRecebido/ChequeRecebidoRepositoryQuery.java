package br.com.wcorrea.transport.api.modulos.financeiro.chequeRecebido;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ChequeRecebidoRepositoryQuery {
    Page<ChequeRecebido> findAll(ChequeRecebidoRepositoryFiltro filtro, Pageable pageable);
}
