package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.pessoa.TipoRelacionamento;
import br.com.wcorrea.transport.api.repository.pessoa.TipoRelacionamento.TipoRelacionamentoRepository;
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
        if (id != null && id > 0) {
            Optional<TipoRelacionamento> obj = tipoRelacionamentoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new TipoRelacionamentoNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new TipoRelacionamentoNaoEncontrado();
        }
    }
}
