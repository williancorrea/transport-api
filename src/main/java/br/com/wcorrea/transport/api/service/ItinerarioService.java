package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Itinerario;
import br.com.wcorrea.transport.api.repository.itinerario.ItinerarioRepository;
import br.com.wcorrea.transport.api.service.exception.veiculo.ItinerarioNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

/**
 * ClasseDespesa responsavel por manipular toda a regra de negocio de um Itinerario
 */
@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    public Itinerario atualizar(Long id, Itinerario itinerario) {
        Itinerario objFound = itinerarioRepository.save(buscarPorId(id));
        itinerario.setId(objFound.getId());
        return itinerarioRepository.save(itinerario);
    }

    public Itinerario buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<Itinerario> obj = itinerarioRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new ItinerarioNaoEncontrado();
    }

    public Itinerario buscarPorId(Itinerario itinerario) {
        if (itinerario == null) {
            throw new ItinerarioNaoEncontrado();
        }
        return this.buscarPorId(itinerario.getId());
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new ItinerarioNaoEncontrado();
        }
    }
}
