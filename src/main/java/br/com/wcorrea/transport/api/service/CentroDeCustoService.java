package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.CentroDeCusto;
import br.com.wcorrea.transport.api.repository.centroDeCusto.CentroDeCustoRepository;
import br.com.wcorrea.transport.api.service.exception.CentroDeCustoNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.ClasseDespesaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

        centroDeCusto.setControle(objFound.getControle());
        centroDeCusto.getControle().setDataAlteracao(new Date());

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
        Optional<CentroDeCusto> centroDeCusto = centroDeCustoRepository.findById(id);
        if (!centroDeCusto.isPresent()) {
            throw new CentroDeCustoNaoEncontrado();
        }
        return centroDeCusto.get();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new CentroDeCustoNaoEncontrado();
        }
    }
}
