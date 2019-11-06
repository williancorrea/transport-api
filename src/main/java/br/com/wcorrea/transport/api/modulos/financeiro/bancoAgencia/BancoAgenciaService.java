package br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia;

import br.com.wcorrea.transport.api.modulos.financeiro.banco.BancoService;
import br.com.wcorrea.transport.api.service.CidadeService;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BancoAgenciaService {

    @Autowired
    private BancoAgenciaRepository bancoAgenciaRepository;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private BancoService bancoService;

    public BancoAgencia salvar(BancoAgencia bancoAgencia) {
        bancoAgencia = prepararObj(bancoAgencia);
        return bancoAgenciaRepository.saveAndFlush(bancoAgencia);
    }

    public BancoAgencia atualizar(Long id, BancoAgencia bancoAgencia) {
        BancoAgencia updateFound = buscarPorId(id);
        bancoAgencia.setId(updateFound.getId());
        bancoAgencia = prepararObj(bancoAgencia);
        return bancoAgenciaRepository.save(bancoAgencia);
    }

    private BancoAgencia prepararObj(BancoAgencia bancoAgencia) {
        bancoAgencia.setCidade(cidadeService.buscarPorId(bancoAgencia.getCidade().getId()));
        bancoAgencia.setBanco(bancoService.buscarPorId(bancoAgencia.getBanco().getId()));
        return bancoAgencia;
    }

    public BancoAgencia buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<BancoAgencia> obj = bancoAgenciaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new BancoAgenciaExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new BancoAgenciaExceptionNaoEncontrado();
        }
    }
}
