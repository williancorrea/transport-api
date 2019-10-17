package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.veiculo.VeiculoMarca;
import br.com.wcorrea.transport.api.repository.QueryFiltroPadrao;
import br.com.wcorrea.transport.api.repository.veiculo.marca.VeiculoMarcaRepository;
import br.com.wcorrea.transport.api.service.exception.RegraDeNegocio;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoMarcaService {

    @Autowired
    private VeiculoMarcaRepository veiculoMarcaRepository;

    public List<VeiculoMarca> listar(QueryFiltroPadrao filtro) {
        return veiculoMarcaRepository.findByNomeContainingIgnoreCase(filtro.getFiltroGlobal());
    }

    public List<VeiculoMarca> listarSomenteAtivo(QueryFiltroPadrao filtro) {
        return veiculoMarcaRepository.findByNomeIsNullOrNomeContainingIgnoreCaseAndInativoFalseOrderByNome(filtro.getFiltroGlobal());
    }

    public void excluir(String key) {
        excluir(descriptografarKey(key));
    }

    public void excluir(Long id) {
        VeiculoMarca veiculo = buscarPorId(id);
        veiculoMarcaRepository.deleteById(veiculo.getId());
    }

    public VeiculoMarca atualizar(Long id, VeiculoMarca veiculo) {
        VeiculoMarca objFound = buscarPorId(id);
        veiculo.setId(objFound.getId());
        return veiculoMarcaRepository.save(veiculo);
    }

    public VeiculoMarca salvar(VeiculoMarca veiculo) {
        veiculo.setId(null);
        return veiculoMarcaRepository.saveAndFlush(veiculo);
    }

    public VeiculoMarca buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<VeiculoMarca> obj = veiculoMarcaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new RegraDeNegocio("Marca/Chassis n\u00e3o encontrado");
    }

    public VeiculoMarca buscarPorId(VeiculoMarca veiculo) {
        if (veiculo == null) {
            throw new RegraDeNegocio("Marca/Chassis n\u00e3o encontrado");
        }
        return this.buscarPorId(veiculo.getId());
    }

    public Long descriptografarKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new RegraDeNegocio("Marca/Chassis n\u00e3o encontrado");
        }
    }
}
