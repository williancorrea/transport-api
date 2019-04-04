package br.com.wcorrea.transport.api.repository.tanqueCombustivel;

import br.com.wcorrea.transport.api.model.TanqueCombustivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TanqueCombustivelRepositoryQuery {
    Page<TanqueCombustivel> findAll(TanqueCombustivelFiltro filtro, Pageable pageable);
}
