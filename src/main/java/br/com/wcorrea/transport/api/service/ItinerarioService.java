package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Itinerario;
import br.com.wcorrea.transport.api.repository.itinerario.ItinerarioRepository;
import br.com.wcorrea.transport.api.service.exception.ItinerarioNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Classe responsavel por manipular toda a regra de negocio de um Itinerario
 */
@Service
public class ItinerarioService {

    @Autowired
    private ItinerarioRepository itinerarioRepository;

    /**
     * Atualizar
     *
     * @param id
     * @param itinerario
     * @return
     */
    public Itinerario atualizar(Long id, Itinerario itinerario) {
        Itinerario objFound = itinerarioRepository.save(buscarPorId(id));
        itinerario.setId(objFound.getId());
        itinerario.setControle(objFound.getControle());
        itinerario.getControle().setDataAlteracao(LocalDateTime.now());
        return itinerarioRepository.save(itinerario);
    }

    /**
     * Buscar por ID
     */
    public Itinerario buscarPorId(Long id) {
        Itinerario itinerario = itinerarioRepository.findOne(id);
        if (itinerario == null) {
            throw new ItinerarioNaoEncontrado();
        }
        return itinerario;
    }

    public Itinerario buscarPorId(Itinerario itinerario) {
        if(itinerario == null){
            throw  new ItinerarioNaoEncontrado();
        }
        return this.buscarPorId(itinerario.getId());
    }
}
