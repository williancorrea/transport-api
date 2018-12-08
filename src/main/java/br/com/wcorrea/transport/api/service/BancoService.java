package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Banco;
import br.com.wcorrea.transport.api.repository.banco.BancoRepository;
import br.com.wcorrea.transport.api.service.exception.BancoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Class responsible for performing the entire business rule by manipulating bank information
 */
@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    /**
     * Update banco
     *
     * @param id
     * @param banco
     * @return
     */
    public Banco update(Long id, Banco banco) {
        Banco updateFound = findOne(id);
        banco.setId(updateFound.getId());
        banco.setControle(updateFound.getControle());
        banco.getControle().setDataAlteracao(new Date());
        return bancoRepository.save(banco);
    }

    /**
     * Find bank by id
     */
    public Banco findOne(Long id) {
        Banco banco = bancoRepository.findOne(id);
        if (banco == null) {
            throw new BancoNaoEncontrado();
        }
        return banco;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new BancoNaoEncontrado();
        }
    }
}
