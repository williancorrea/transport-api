package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.TipoRelacionamento;
import br.com.wcorrea.transport.api.repository.TipoRelacionamento.TipoRelacionamentoRepository;
import br.com.wcorrea.transport.api.service.exception.TipoRelacionamentoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsável por executar toda a regra de negócios, manipulando o tipo de informação de relacionamento
 */
@Service
public class TipoRelacionamentoService {

    @Autowired
    private TipoRelacionamentoRepository tipoRelacionamentoRepository;

    /**
     * Update tipo of relationship
     *
     * @param id
     * @param tipoRelacionamento
     * @return
     */
    public TipoRelacionamento update(Long id, TipoRelacionamento tipoRelacionamento) {
        TipoRelacionamento objFound = tipoRelacionamentoRepository.save(findOne(id));
        tipoRelacionamento.setId(objFound.getId());
        return tipoRelacionamentoRepository.save(tipoRelacionamento);
    }

    /**
     * Find tipo of relationship by id
     */
    private TipoRelacionamento findOne(Long id) {
        TipoRelacionamento tipoRelacionamento = tipoRelacionamentoRepository.findOne(id);
        if (tipoRelacionamento == null) {
            throw new TipoRelacionamentoNaoEncontrado();
        }
        return tipoRelacionamento;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new TipoRelacionamentoNaoEncontrado();
        }
    }
}
