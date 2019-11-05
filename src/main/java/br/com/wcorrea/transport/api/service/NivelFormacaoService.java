package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.pessoa.NivelFormacao;
import br.com.wcorrea.transport.api.repository.pessoa.NivelFormacao.NivelFormacaoRepository;
import br.com.wcorrea.transport.api.service.exception.NivelEducacaoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NivelFormacaoService {

    @Autowired
    private NivelFormacaoRepository nivelFormacaoRepository;

    public NivelFormacao update(Long id, NivelFormacao levelOfEducation) {
        NivelFormacao objFound = nivelFormacaoRepository.save(findOne(id));
        levelOfEducation.setId(objFound.getId());
        return nivelFormacaoRepository.save(levelOfEducation);
    }

    private NivelFormacao findOne(Long id) {
        if (id != null && id > 0) {
            Optional<NivelFormacao> obj = nivelFormacaoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new NivelEducacaoNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new NivelEducacaoNaoEncontrado();
        }
    }
}
