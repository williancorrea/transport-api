package br.com.wcorrea.transport.api.modulos.financeiro.planoConta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface PlanoContaRepositoryQuery {
    Page<PlanoConta> findAll(PlanoContaRepositoryFiltro filtro, Pageable pageable);
}
