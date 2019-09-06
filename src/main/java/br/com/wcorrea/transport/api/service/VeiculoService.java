package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.repository.veiculo.VeiculoRepository;
import br.com.wcorrea.transport.api.service.exception.veiculo.VeiculoJaCadastrado;
import br.com.wcorrea.transport.api.service.exception.veiculo.VeiculoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public Veiculo atualizar(Long id, Veiculo veiculo) {
        Veiculo objFound = veiculoRepository.save(buscarPorId(id));
        veiculo.setId(objFound.getId());
        return veiculoRepository.save(veiculo);
    }

    public Veiculo salvar(Veiculo veiculo) {
        if (veiculoExiste(veiculo.getPlaca())) {
            throw new VeiculoJaCadastrado();
        }
        return veiculoRepository.saveAndFlush(veiculo);
    }

    public Veiculo buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<Veiculo> obj = veiculoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new VeiculoNaoEncontrado();
    }

    public Veiculo buscarPorId(Veiculo veiculo) {
        if (veiculo == null) {
            throw new VeiculoNaoEncontrado();
        }
        return this.buscarPorId(veiculo.getId());
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new VeiculoNaoEncontrado();
        }
    }

    public Veiculo buscarPorPlaca(String placa) {
        Veiculo veiculo = veiculoRepository.findByPlaca(placa);
        if (veiculo == null) {
            throw new VeiculoNaoEncontrado();
        }
        return veiculo;
    }

    public boolean veiculoExiste(String placa) {
        Veiculo veiculo = veiculoRepository.findByPlaca(placa);
        if (veiculo == null) {
            return false;
        }
        return true;
    }
}
