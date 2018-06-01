package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.service.exception.EstadoCivilNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Class responsible for performing the entire business rule by manipulating information
 */
@Service
public class EstadoCivilService {

    @Autowired
    private EstadoCivilRepository maritalStatusRepository;

    /**
     * ATUALIZA O OBJETO
     *
     * @param id
     * @param estadoCivil
     * @return
     */
    public EstadoCivil update(Long id, EstadoCivil estadoCivil) {
        EstadoCivil objFound = maritalStatusRepository.save(findOne(id));
        estadoCivil.setId(objFound.getId());
        estadoCivil.setControle(objFound.getControle());
        estadoCivil.getControle().setModificationDate(LocalDateTime.now());
        return maritalStatusRepository.save(estadoCivil);
    }

    /**
     * BUSCAR POR ID
     */
    public EstadoCivil findOne(Long id) {
        EstadoCivil estadoCivil = maritalStatusRepository.findOne(id);
        if (estadoCivil == null) {
            throw new EstadoCivilNaoEncontrado();
        }
        return estadoCivil;
    }
}
