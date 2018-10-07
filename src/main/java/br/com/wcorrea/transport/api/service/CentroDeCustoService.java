package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.CentroDeCusto;
import br.com.wcorrea.transport.api.repository.centroDeCusto.CentroDeCustoRepository;
import br.com.wcorrea.transport.api.service.exception.CentroDeCustoNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.ClasseDespezaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * centroDeCusto responsavel por manipular todas as informcoes do objeto Centro de Custo
 */
@Service
public class CentroDeCustoService {

    @Autowired
    private CentroDeCustoRepository centroDeCustoRepository;

    @Autowired
    private ClasseDespesaService classeDespesaService;

    /**
     * Atualizar
     *
     * @param id
     * @param centroDeCusto
     * @return
     */
    public CentroDeCusto atualizar(Long id, CentroDeCusto centroDeCusto) {
        CentroDeCusto objFound = centroDeCustoRepository.save(buscarPorId(id));
        centroDeCusto.setId(objFound.getId());

        centroDeCusto.setControle(objFound.getControle());
        centroDeCusto.getControle().setDataAlteracao(new Date());

        return salvar(centroDeCusto);
    }

    public CentroDeCusto salvar(CentroDeCusto centroDeCusto) {
        centroDeCusto.setClasseDespesa(classeDespesaService.buscarPorId(centroDeCusto.getClasseDespesa().getId()));
        if(centroDeCusto.getClasseDespesa() == null){
            throw new ClasseDespezaNaoEncontrada();
        }
        return centroDeCustoRepository.saveAndFlush(centroDeCusto);
    }

    /**
     * Buscar por ID
     */
    public CentroDeCusto buscarPorId(Long id) {
        CentroDeCusto centroDeCusto = centroDeCustoRepository.findOne(id);
        if (centroDeCusto == null) {
            throw new CentroDeCustoNaoEncontrado();
        }
        return centroDeCusto;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new CentroDeCustoNaoEncontrado();
        }
    }
}
