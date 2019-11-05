package br.com.wcorrea.transport.api.modulos.financeiro.banco;

import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepository;

    public Banco update(Long id, Banco banco) {
        Banco updateFound = buscarPorId(id);
        banco.setId(updateFound.getId());
        return bancoRepository.save(banco);
    }

    public Banco buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<Banco> obj = bancoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new BancoExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new BancoExceptionNaoEncontrado();
        }
    }
}
