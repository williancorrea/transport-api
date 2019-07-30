package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Banco;
import br.com.wcorrea.transport.api.repository.banco.BancoRepository;
import br.com.wcorrea.transport.api.service.exception.BancoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    public Banco update(Long id, Banco banco) {
        Banco updateFound = findOne(id);
        banco.setId(updateFound.getId());
        banco.setControle(updateFound.getControle());
        banco.getControle().setDataAlteracao(new Date());
        return bancoRepository.save(banco);
    }

    public Banco findOne(Long id) {
        Optional<Banco> banco = bancoRepository.findById(id);
        if (!banco.isPresent()) {
            throw new BancoNaoEncontrado();
        }
        return banco.get();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new BancoNaoEncontrado();
        }
    }
}
