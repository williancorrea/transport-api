package br.com.wcorrea.transport.api.repository.fretamento;

import br.com.wcorrea.transport.api.model.Fretamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FretamentoRepositoryQuery {
    Page<Fretamento> findAll(FretamentoFiltro fretamentoFiltro, Pageable pageable);
}
