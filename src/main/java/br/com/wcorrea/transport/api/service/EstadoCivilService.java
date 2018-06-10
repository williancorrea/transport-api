package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.service.exception.EstadoCivilNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Class responsible for performing the entire business rule by manipulating information
 * Classe responsavel por manipular toda a regra de negocio de um EstadoCivil
 */
@Service
public class EstadoCivilService {

    @Autowired
    private EstadoCivilRepository maritalStatusRepository;

    /**
     * Atualizar
     *
     * @param id
     * @param estadoCivil
     * @return
     */
    public EstadoCivil atualizar(Long id, EstadoCivil estadoCivil) {
        EstadoCivil objFound = maritalStatusRepository.save(buscarPorId(id));
        estadoCivil.setId(objFound.getId());
        estadoCivil.setControle(objFound.getControle());
        estadoCivil.getControle().setDataAlteracao(LocalDateTime.now());
        return maritalStatusRepository.save(estadoCivil);
    }

    /**
     * Buscar por ID
     */
    public EstadoCivil buscarPorId(Long id) {
        EstadoCivil estadoCivil = maritalStatusRepository.findOne(id);
        if (estadoCivil == null) {
            throw new EstadoCivilNaoEncontrado();
        }
        return estadoCivil;
    }
}
