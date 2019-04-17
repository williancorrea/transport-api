package br.com.wcorrea.transport.api.repository.pessoa.Colaborador;

import br.com.wcorrea.transport.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Pessoa, Long>, ColaboradorRepositoryQuery {
}
