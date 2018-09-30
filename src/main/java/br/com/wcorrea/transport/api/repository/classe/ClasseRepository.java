package br.com.wcorrea.transport.api.repository.classe;

import br.com.wcorrea.transport.api.model.Classe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseRepository extends JpaRepository<Classe, Long>, ClasseRepositoryQuery {

}