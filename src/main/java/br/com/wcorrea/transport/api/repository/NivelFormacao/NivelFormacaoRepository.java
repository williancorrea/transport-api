package br.com.wcorrea.transport.api.repository.NivelFormacao;

import br.com.wcorrea.transport.api.model.NivelFormacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NivelFormacaoRepository extends JpaRepository<NivelFormacao, Long>, NivelFormacaoRepositoryQuery {
}