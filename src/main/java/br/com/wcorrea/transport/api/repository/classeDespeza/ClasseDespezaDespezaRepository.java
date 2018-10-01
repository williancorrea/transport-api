package br.com.wcorrea.transport.api.repository.classeDespeza;

import br.com.wcorrea.transport.api.model.ClasseDespeza;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClasseDespezaDespezaRepository extends JpaRepository<ClasseDespeza, Long>, ClasseDespezaRepositoryQuery {

}