package br.com.wcorrea.transport.api.repository.fretamentoEventual;

import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventual;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface FretamentoEventualRepositoryQuery {
    Page<FretamentoEventual> findAll(FretamentoEventualFiltro fretamentoEventualFiltro, Pageable pageable);
}
