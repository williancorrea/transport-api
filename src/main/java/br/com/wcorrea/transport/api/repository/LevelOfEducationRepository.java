package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.LevelOfEducation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LevelOfEducationRepository extends JpaRepository<LevelOfEducation, Long>, LevelOfEducationRepositoryQuery {
}
