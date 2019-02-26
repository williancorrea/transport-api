package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.UnidadeMedida;
import br.com.wcorrea.transport.api.repository.UnidadeMedida.UnidadeMedidaRepository;
import br.com.wcorrea.transport.api.service.exception.UnidadeMedidaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Class responsible for performing the entire business rule by manipulating product unit information
 */
@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    /**
     * Update product unit
     *
     * @param id
     * @param unidadeMedida
     * @return
     */
    public UnidadeMedida update(Long id, UnidadeMedida unidadeMedida) {
        UnidadeMedida updateFound = findOne(id);
        unidadeMedida.setId(updateFound.getId());
        return unidadeMedidaRepository.save(unidadeMedida);
    }

    /**
     * Find product unit by id
     */
    private UnidadeMedida findOne(Long id) {
        UnidadeMedida unidadeMedida = unidadeMedidaRepository.findOne(id);
        if (unidadeMedida == null) {
            throw new UnidadeMedidaNaoEncontrada();
        }
        return unidadeMedida;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new UnidadeMedidaNaoEncontrada();
        }
    }
}
