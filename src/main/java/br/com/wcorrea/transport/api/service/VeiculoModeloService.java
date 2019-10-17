package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.veiculo.VeiculoModelo;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.repository.veiculo.modelo.VeiculoModeloRepository;
import br.com.wcorrea.transport.api.service.exception.RegraDeNegocio;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoModeloService {

    @Autowired
    private VeiculoModeloRepository veiculoModeloRepository;

    public List<VeiculoModelo> listar(QueryFiltroPadrao filtro) {
        return veiculoModeloRepository.findByNomeContainingIgnoreCase(filtro.getFiltroGlobal());
    }

    public List<VeiculoModelo> listarSomenteAtivo(QueryFiltroPadrao filtro) {
        return veiculoModeloRepository.findByNomeIsNullOrNomeContainingIgnoreCaseAndInativoFalseOrderByNome(filtro.getFiltroGlobal());
    }

    public void excluir(String key) {
        excluir(descriptografarKey(key));
    }

    public void excluir(Long id) {
        VeiculoModelo veiculo = buscarPorId(id);
        veiculoModeloRepository.deleteById(veiculo.getId());
    }

    public VeiculoModelo atualizar(Long id, VeiculoModelo veiculo) {
        VeiculoModelo objFound = buscarPorId(id);
        veiculo.setId(objFound.getId());
        return veiculoModeloRepository.save(veiculo);
    }

    public VeiculoModelo salvar(VeiculoModelo veiculo) {
        veiculo.setId(null);
        return veiculoModeloRepository.saveAndFlush(veiculo);
    }

    public VeiculoModelo buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<VeiculoModelo> obj = veiculoModeloRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new RegraDeNegocio("Modelo/Chassis n\u00e3o encontrado");
    }

    public VeiculoModelo buscarPorId(VeiculoModelo veiculo) {
        if (veiculo == null) {
            throw new RegraDeNegocio("Modelo/Chassis n\u00e3o encontrado");
        }
        return this.buscarPorId(veiculo.getId());
    }

    public Long descriptografarKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new RegraDeNegocio("Modelo/Chassis n\u00e3o encontrado");
        }
    }
}
