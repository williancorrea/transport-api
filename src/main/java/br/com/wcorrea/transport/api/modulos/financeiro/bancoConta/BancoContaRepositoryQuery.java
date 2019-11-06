package br.com.wcorrea.transport.api.modulos.financeiro.bancoConta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BancoContaRepositoryQuery {
    Page<BancoConta> findAll(BancoContaRepositoryFiltro filtro, Pageable pageable);
}
