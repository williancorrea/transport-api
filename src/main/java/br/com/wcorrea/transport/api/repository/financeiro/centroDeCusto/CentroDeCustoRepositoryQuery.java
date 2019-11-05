package br.com.wcorrea.transport.api.repository.financeiro.centroDeCusto;

import br.com.wcorrea.transport.api.model.CentroDeCusto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CentroDeCustoRepositoryQuery {
    Page<CentroDeCusto> findAll(CentroDeCustoFiltro centroDeCustoFiltro, Pageable pageable);
}
