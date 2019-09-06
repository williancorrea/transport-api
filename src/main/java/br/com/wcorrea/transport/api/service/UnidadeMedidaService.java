package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.UnidadeMedida;
import br.com.wcorrea.transport.api.repository.UnidadeMedida.UnidadeMedidaRepository;
import br.com.wcorrea.transport.api.service.exception.UnidadeMedidaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public UnidadeMedida update(Long id, UnidadeMedida unidadeMedida) {
        UnidadeMedida updateFound = findOne(id);
        unidadeMedida.setId(updateFound.getId());

        return unidadeMedidaRepository.save(unidadeMedida);
    }

    private UnidadeMedida findOne(Long id) {
        if (id != null && id > 0) {
            Optional<UnidadeMedida> obj = unidadeMedidaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new UnidadeMedidaNaoEncontrada();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new UnidadeMedidaNaoEncontrada();
        }
    }
}
