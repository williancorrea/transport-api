package br.com.wcorrea.transport.api.repository.veiculo;

import br.com.wcorrea.transport.api.model.Veiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface VeiculoRepositoryQuery {
    Page<Veiculo> findAll(VeiculoFiltro filtro, Pageable paginacao);
}
