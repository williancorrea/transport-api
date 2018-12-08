package br.com.wcorrea.transport.api.repository.banco;

import br.com.wcorrea.transport.api.model.Banco;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface BancoRepositoryQuery {
    Page<Banco> findAll(BancoFiltro bancoFiltro, Pageable pageable);
}
