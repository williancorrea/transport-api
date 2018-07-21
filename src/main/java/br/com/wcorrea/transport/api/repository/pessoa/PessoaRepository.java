package br.com.wcorrea.transport.api.repository.pessoa;

import br.com.wcorrea.transport.api.model.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long>, PessoaRepositoryQuery {
}
