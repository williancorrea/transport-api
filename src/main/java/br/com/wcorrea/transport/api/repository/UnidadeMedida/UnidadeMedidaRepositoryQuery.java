package br.com.wcorrea.transport.api.repository.UnidadeMedida;

import br.com.wcorrea.transport.api.model.UnidadeMedida;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UnidadeMedidaRepositoryQuery {
    Page<UnidadeMedida> findAll(UnidadeMedidaFilter unidadeMedidaFilter, Pageable pageable);
}
