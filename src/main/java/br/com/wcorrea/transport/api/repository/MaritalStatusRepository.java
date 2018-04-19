package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.MaritalStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaritalStatusRepository extends JpaRepository<MaritalStatus, Long>, MaritalStatusRepositoryQuery {
}
