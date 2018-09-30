package br.com.wcorrea.transport.api.repository.classe;

import br.com.wcorrea.transport.api.model.Classe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ClasseRepositoryQuery {
    Page<Classe> findAll(ClasseFiltro classeFiltro, Pageable pageable);
}
