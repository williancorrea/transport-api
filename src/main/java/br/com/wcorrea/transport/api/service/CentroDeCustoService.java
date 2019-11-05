package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.CentroDeCusto;
import br.com.wcorrea.transport.api.repository.financeiro.centroDeCusto.CentroDeCustoRepository;
import br.com.wcorrea.transport.api.service.exception.CentroDeCustoNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.ClasseDespesaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CentroDeCustoService {

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Autowired
    private ClasseDespesaService classeDespesaService;

    public CentroDeCusto atualizar(Long id, CentroDeCusto centroDeCusto) {
        CentroDeCusto objFound = centroDeCustoRepository.save(buscarPorId(id));
        centroDeCusto.setId(objFound.getId());

        return salvar(centroDeCusto);
    }

    public CentroDeCusto salvar(CentroDeCusto centroDeCusto) {
        centroDeCusto.setClasseDespesa(classeDespesaService.buscarPorId(centroDeCusto.getClasseDespesa().getId()));
        if (centroDeCusto.getClasseDespesa() == null) {
            throw new ClasseDespesaNaoEncontrada();
        }
        return centroDeCustoRepository.saveAndFlush(centroDeCusto);
    }

    public CentroDeCusto buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<CentroDeCusto> obj = centroDeCustoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new CentroDeCustoNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new CentroDeCustoNaoEncontrado();
        }
    }
}
