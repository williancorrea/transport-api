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
        unidadeMedida.setControle(updateFound.getControle());

        return unidadeMedidaRepository.save(unidadeMedida);
    }

    private UnidadeMedida findOne(Long id) {
        Optional<UnidadeMedida> unidadeMedida = unidadeMedidaRepository.findById(id);
        if (!unidadeMedida.isPresent()) {
            throw new UnidadeMedidaNaoEncontrada();
        }
        return unidadeMedida.get();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new UnidadeMedidaNaoEncontrada();
        }
    }
}
