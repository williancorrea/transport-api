package br.com.wcorrea.transport.api.modulos.financeiro.bancoConta;

import br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia.BancoAgenciaService;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BancoContaService {

    @Autowired
    private BancoContaRepository bancoContaRepository;

    @Autowired
    private BancoAgenciaService bancoAgenciaService;

    public BancoConta salvar(BancoConta bancoConta) {
        bancoConta = prepararObj(bancoConta);
        return bancoContaRepository.saveAndFlush(bancoConta);
    }

    public BancoConta atualizar(Long id, BancoConta bancoConta) {
        BancoConta updateFound = buscarPorId(id);
        bancoConta.setId(updateFound.getId());
        bancoConta = prepararObj(bancoConta);
        return bancoContaRepository.save(bancoConta);
    }

    private BancoConta prepararObj(BancoConta bancoConta) {
        bancoConta.setBancoAgencia(bancoAgenciaService.buscarPorId(bancoConta.getBancoAgencia().getId()));
        return bancoConta;
    }

    public BancoConta buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<BancoConta> obj = bancoContaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new BancoContaExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new BancoContaExceptionNaoEncontrado();
        }
    }
}
