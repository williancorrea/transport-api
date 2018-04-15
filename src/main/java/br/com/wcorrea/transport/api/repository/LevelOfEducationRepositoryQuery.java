package br.com.wcorrea.transport.api.repository;

import br.com.wcorrea.transport.api.model.LevelOfEducation;
import br.com.wcorrea.transport.api.repository.filter.LevelOfEducationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface LevelOfEducationRepositoryQuery {
    Page<LevelOfEducation> findAll(LevelOfEducationFilter levelOdEducationFilter, Pageable pageable);
}
