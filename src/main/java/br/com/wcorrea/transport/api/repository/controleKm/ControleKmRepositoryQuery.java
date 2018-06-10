package br.com.wcorrea.transport.api.repository.controleKm;

import br.com.wcorrea.transport.api.model.ControleKm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ControleKmRepositoryQuery {
    Page<ControleKm> findAll(ControleKmFiltro filtro, Pageable paginacao);
}
