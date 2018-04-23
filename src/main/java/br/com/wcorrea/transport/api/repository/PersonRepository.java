package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>, PersonRepositoryQuery {
}
