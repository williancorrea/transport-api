package br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BancoAgenciaRepositoryQuery {
    Page<BancoAgencia> findAll(BancoAgenciaRepositoryFiltro filtro, Pageable pageable);
}
