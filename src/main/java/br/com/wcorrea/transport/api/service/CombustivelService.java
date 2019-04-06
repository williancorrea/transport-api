package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Combustivel;
import br.com.wcorrea.transport.api.repository.combustivel.CombustivelRepository;
import br.com.wcorrea.transport.api.service.exception.abastecimento.CombustivelNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Class responsible for performing the entire business rule by manipulating bank information
 */
@Service
public class CombustivelService {

    @Autowired
    private CombustivelRepository combustivelRepository;

    /**
     * Update
     *
     * @param id
     * @param combustivel
     * @return
     */
    public Combustivel update(Long id, Combustivel combustivel) {
        Combustivel updateFound = findOne(id);
        combustivel.setId(updateFound.getId());
        combustivel.setControle(updateFound.getControle());
        combustivel.getControle().setDataAlteracao(new Date());
        return combustivelRepository.save(combustivel);
    }

    /**
     * Find by id
     */
    public Combustivel findOne(Long id) {
        Combustivel combustivel = combustivelRepository.findOne(id);
        if (combustivel == null) {
            throw new CombustivelNaoEncontrado();
        }
        return combustivel;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new CombustivelNaoEncontrado();
        }
    }
}