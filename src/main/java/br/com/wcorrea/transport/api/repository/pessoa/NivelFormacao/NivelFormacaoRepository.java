package br.com.wcorrea.transport.api.repository.pessoa.NivelFormacao;

import br.com.wcorrea.transport.api.model.pessoa.NivelFormacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelFormacaoRepository extends JpaRepository<NivelFormacao, Long>, NivelFormacaoRepositoryQuery {
}
