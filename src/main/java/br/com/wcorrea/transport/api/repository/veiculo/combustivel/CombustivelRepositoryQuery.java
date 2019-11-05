package br.com.wcorrea.transport.api.repository.veiculo.combustivel;

import br.com.wcorrea.transport.api.model.Combustivel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CombustivelRepositoryQuery {
    Page<Combustivel> findAll(CombustivelFiltro filtro, Pageable pageable);
}
