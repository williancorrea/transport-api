package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.LevelOfEducation;
import br.com.wcorrea.transport.api.repository.LevelOfEducationRepository;
import br.com.wcorrea.transport.api.service.exception.LevelOfEducationNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Class responsible for performing the entire business rule by manipulating information
 */
@Service
public class LevelOfEducationService {

    @Autowired
    private LevelOfEducationRepository levelOfEducationRepository;

    /**
     * Update
     *
     * @param id
     * @param levelOfEducation
     * @return
     */
    public LevelOfEducation update(Long id, LevelOfEducation levelOfEducation) {
        LevelOfEducation objFound = levelOfEducationRepository.save(findOne(id));
        levelOfEducation.setId(objFound.getId());
        levelOfEducation.setProperties(objFound.getProperties());
        levelOfEducation.getProperties().setDataAlteracao(LocalDateTime.now());
        return levelOfEducationRepository.save(levelOfEducation);
    }

    /**
     * Find tipo obj by id
     */
    private LevelOfEducation findOne(Long id) {
        LevelOfEducation levelOfEducation = levelOfEducationRepository.findOne(id);
        if (levelOfEducation == null) {
            throw new LevelOfEducationNotFound();
        }
        return levelOfEducation;
    }
}
