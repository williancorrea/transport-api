package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.repository.veiculo.VeiculoRepository;
import br.com.wcorrea.transport.api.service.exception.VeiculoNaoEncontrado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Classe responsavel por manipular toda a regra de negocio de um Veiculo
 */
@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    /**
     * Atualizar
     *
     * @param id
     * @param veiculo
     * @return
     */
    public Veiculo atualizar(Long id, Veiculo veiculo) {
        Veiculo objFound = veiculoRepository.save(buscarPorId(id));
        veiculo.setId(objFound.getId());
        veiculo.setControle(objFound.getControle());
        veiculo.getControle().setDataAlteracao(LocalDateTime.now());
        return veiculoRepository.save(veiculo);
    }

    /**
     * Buscar por ID
     */
    public Veiculo buscarPorId(Long id) {
        Veiculo veiculo = veiculoRepository.findOne(id);
        if (veiculo == null) {
            throw new VeiculoNaoEncontrado();
        }
        return veiculo;
    }

    public Veiculo buscarPorId(Veiculo veiculo) {
        if(veiculo == null){
            throw new VeiculoNaoEncontrado();
        }
        return this.buscarPorId(veiculo.getId());
    }
}
