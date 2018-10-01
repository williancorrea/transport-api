package br.com.wcorrea.transport.api.repository.classeDespeza;

import br.com.wcorrea.transport.api.model.ClasseDespeza;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClasseDespezaRepositoryQuery {
    Page<ClasseDespeza> findAll(ClasseDespezaFiltro classeDespezaFiltro, Pageable pageable);
}
