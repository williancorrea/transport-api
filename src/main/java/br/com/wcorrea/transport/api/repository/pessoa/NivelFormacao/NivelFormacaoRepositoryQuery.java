package br.com.wcorrea.transport.api.repository.pessoa.NivelFormacao;

import br.com.wcorrea.transport.api.model.pessoa.NivelFormacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface NivelFormacaoRepositoryQuery {
    Page<NivelFormacao> findAll(NivelFormacaoFilter levelOdEducationFilter, Pageable pageable);
}
