package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ConfiguracaoSistema;
import br.com.wcorrea.transport.api.repository.ConfiguracaoSistemaRepository;
import br.com.wcorrea.transport.api.service.exception.ConfiguracaoSistemaNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConfiguracaoSistemaService {

    @Autowired
    private ConfiguracaoSistemaRepository configuracaoSistemaRepository;

    public ConfiguracaoSistema atualizar(Long id, ConfiguracaoSistema configuracaoSistema) {
        ConfiguracaoSistema objFound = configuracaoSistemaRepository.save(buscarPorId(id));
        configuracaoSistema.setId(objFound.getId());
        return salvar(configuracaoSistema);
    }

    public ConfiguracaoSistema salvar(ConfiguracaoSistema configuracaoSistema) {
        return configuracaoSistemaRepository.saveAndFlush(configuracaoSistema);
    }

    public ConfiguracaoSistema buscarPorId(Long id) {
        Optional<ConfiguracaoSistema> configuracaoSistema = configuracaoSistemaRepository.findById(id);
        if (!configuracaoSistema.isPresent()) {
            throw new ConfiguracaoSistemaNaoEncontrado();
        }
        return configuracaoSistema.get();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new ConfiguracaoSistemaNaoEncontrado();
        }
    }

}
