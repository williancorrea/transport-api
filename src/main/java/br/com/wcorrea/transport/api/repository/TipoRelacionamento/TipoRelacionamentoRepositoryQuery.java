package br.com.wcorrea.transport.api.repository.TipoRelacionamento;

import br.com.wcorrea.transport.api.model.TipoRelacionamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TipoRelacionamentoRepositoryQuery {
    Page<TipoRelacionamento> findAll(TipoRelacionamentoFilter tipoRelacionamentoFilter, Pageable pageable);
}
