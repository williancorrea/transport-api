package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.MaritalStatus;
import br.com.wcorrea.transport.api.repository.MaritalStatusRepository;
import br.com.wcorrea.transport.api.service.exception.MaritalStatusNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Class responsible for performing the entire business rule by manipulating information
 */
@Service
public class MaritalStatusService {

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    /**
     * Update
     *
     * @param id
     * @param maritalStatus
     * @return
     */
    public MaritalStatus update(Long id, MaritalStatus maritalStatus) {
        MaritalStatus objFound = maritalStatusRepository.save(findOne(id));
        maritalStatus.setId(objFound.getId());
        maritalStatus.setProperties(objFound.getProperties());
        maritalStatus.getProperties().setModificationDate(LocalDateTime.now());
        return maritalStatusRepository.save(maritalStatus);
    }

    /**
     * Find tipo obj by id
     */
    public MaritalStatus findOne(Long id) {
        MaritalStatus maritalStatus = maritalStatusRepository.findOne(id);
        if (maritalStatus == null) {
            throw new MaritalStatusNotFound();
        }
        return maritalStatus;
    }
}
