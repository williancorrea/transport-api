package br.com.wcorrea.transport.api.repository.estadoCidade;

import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CidadeRepository extends JpaRepository<Cidade, Long>, CidadeRepositoryQuery {

}
