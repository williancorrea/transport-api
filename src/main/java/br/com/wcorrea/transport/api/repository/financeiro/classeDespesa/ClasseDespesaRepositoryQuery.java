package br.com.wcorrea.transport.api.repository.financeiro.classeDespesa;

import br.com.wcorrea.transport.api.model.ClasseDespesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClasseDespesaRepositoryQuery {
    Page<ClasseDespesa> findAll(ClasseDespesaFiltro classeDespesaFiltro, Pageable pageable);
}
