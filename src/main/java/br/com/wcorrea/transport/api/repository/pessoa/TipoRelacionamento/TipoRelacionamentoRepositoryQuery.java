package br.com.wcorrea.transport.api.repository.pessoa.TipoRelacionamento;

import br.com.wcorrea.transport.api.model.pessoa.TipoRelacionamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface TipoRelacionamentoRepositoryQuery {
    Page<TipoRelacionamento> findAll(TipoRelacionamentoFilter tipoRelacionamentoFilter, Pageable pageable);
}
