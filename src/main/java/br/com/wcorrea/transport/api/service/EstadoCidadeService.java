package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Cidade;
import br.com.wcorrea.transport.api.model.Estado;
import br.com.wcorrea.transport.api.repository.CidadeRepository;
import br.com.wcorrea.transport.api.repository.EstadoRepository;
import br.com.wcorrea.transport.api.service.exception.CidadeNaoEncontrada;
import br.com.wcorrea.transport.api.service.exception.EstadoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoCidadeService {

    @Autowired
    private EstadoRepository estadoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Estado buscarEstadoPorId(Long id) {
        Optional<Estado> estado = estadoRepository.findById(id);
        if (!estado.isPresent()) {
            throw new EstadoNaoEncontrado();
        }
        return estado.get();
    }

    public List<Cidade> buscarCidadePorEstado(Long estadoId) {
        List<Cidade> cidades = cidadeRepository.findByEstadoId(estadoId);
        if (cidades == null) {
            throw new CidadeNaoEncontrada();
        }
        return cidades;
    }

    public Long buscarEstadoPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new EstadoNaoEncontrado();
        }
    }

    public Long buscarCidadePorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new CidadeNaoEncontrada();
        }
    }

}
