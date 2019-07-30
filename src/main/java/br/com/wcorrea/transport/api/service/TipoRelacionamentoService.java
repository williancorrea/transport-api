package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.TipoRelacionamento;
import br.com.wcorrea.transport.api.repository.TipoRelacionamento.TipoRelacionamentoRepository;
import br.com.wcorrea.transport.api.service.exception.TipoRelacionamentoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoRelacionamentoService {

    @Autowired
    private TipoRelacionamentoRepository tipoRelacionamentoRepository;

    public TipoRelacionamento update(Long id, TipoRelacionamento tipoRelacionamento) {
        TipoRelacionamento objFound = tipoRelacionamentoRepository.save(findOne(id));
        tipoRelacionamento.setId(objFound.getId());
        return tipoRelacionamentoRepository.save(tipoRelacionamento);
    }

    private TipoRelacionamento findOne(Long id) {
        Optional<TipoRelacionamento> tipoRelacionamento = tipoRelacionamentoRepository.findById(id);
        if (!tipoRelacionamento.isPresent()) {
            throw new TipoRelacionamentoNaoEncontrado();
        }
        return tipoRelacionamento.get();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new TipoRelacionamentoNaoEncontrado();
        }
    }
}
