package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.TanqueCombustivel;
import br.com.wcorrea.transport.api.repository.tanqueCombustivel.TanqueCombustivelRepository;
import br.com.wcorrea.transport.api.service.exception.abastecimento.CombustivelNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.abastecimento.TanqueCombustivelNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

/**
 * Class responsible for performing the entire business rule by manipulating bank information
 */
@Service
public class TanqueCombustivelService {

    @Autowired
    private TanqueCombustivelRepository tanqueCombustivelRepository;

    @Autowired
    private CombustivelService combustivelService;

    /**
     * Update
     *
     * @param id
     * @param tanqueCombustivel
     * @return
     */
    public TanqueCombustivel update(Long id, TanqueCombustivel tanqueCombustivel) {
        TanqueCombustivel updateFound = findOne(id);
        tanqueCombustivel.setId(updateFound.getId());
        tanqueCombustivel.setControle(updateFound.getControle());
        tanqueCombustivel.getControle().setDataAlteracao(new Date());

        tanqueCombustivel.setCombustivel(combustivelService.findOne(tanqueCombustivel.getCombustivel().getId()));
        return tanqueCombustivelRepository.save(tanqueCombustivel);
    }

    public TanqueCombustivel findOne(Long id) {
        Optional<TanqueCombustivel> tanqueCombustivel = tanqueCombustivelRepository.findById(id);
        if (!tanqueCombustivel.isPresent()) {
            throw new CombustivelNaoEncontrado();
        }
        return tanqueCombustivel.get();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new TanqueCombustivelNaoEncontrado();
        }
    }
}
