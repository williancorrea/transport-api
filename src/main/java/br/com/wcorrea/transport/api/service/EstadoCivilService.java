package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.pessoa.EstadoCivil;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.service.exception.EstadoCivilNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class EstadoCivilService {

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    public EstadoCivil atualizar(Long id, EstadoCivil estadoCivil) {
        EstadoCivil objFound = estadoCivilRepository.save(buscarPorId(id));
        estadoCivil.setId(objFound.getId());
        estadoCivil.setControle(objFound.getControle());
        estadoCivil.getControle().setDataAlteracao(new Date());
        return estadoCivilRepository.save(estadoCivil);
    }

    public EstadoCivil buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<EstadoCivil> obj = estadoCivilRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new EstadoCivilNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new EstadoCivilNaoEncontrado();
        }
    }
}
