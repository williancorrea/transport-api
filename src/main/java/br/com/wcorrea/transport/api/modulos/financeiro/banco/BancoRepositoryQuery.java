package br.com.wcorrea.transport.api.modulos.financeiro.banco;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BancoRepositoryQuery {
    Page<Banco> findAll(BancoRepositoryFiltro bancoRepositoryFiltro, Pageable pageable);
}
