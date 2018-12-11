package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.NivelFormacao;
import br.com.wcorrea.transport.api.repository.NivelFormacao.NivelFormacaoRepository;
import br.com.wcorrea.transport.api.service.exception.NivelEducacaoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for performing the entire business rule by manipulating information
 */
@Service
public class NivelFormacaoService {

    @Autowired
    private NivelFormacaoRepository nivelFormacaoRepository;

    /**
     * Update
     *
     * @param id
     * @param levelOfEducation
     * @return
     */
    public NivelFormacao update(Long id, NivelFormacao levelOfEducation) {
        NivelFormacao objFound = nivelFormacaoRepository.save(findOne(id));
        levelOfEducation.setId(objFound.getId());
        return nivelFormacaoRepository.save(levelOfEducation);
    }

    /**
     * Find tipo obj by id
     */
    private NivelFormacao findOne(Long id) {
        NivelFormacao levelOfEducation = nivelFormacaoRepository.findOne(id);
        if (levelOfEducation == null) {
            throw new NivelEducacaoNaoEncontrado();
        }
        return levelOfEducation;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new NivelEducacaoNaoEncontrado();
        }
    }
}
