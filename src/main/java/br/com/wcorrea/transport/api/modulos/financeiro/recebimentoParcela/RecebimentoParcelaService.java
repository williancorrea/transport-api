package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela;

import br.com.wcorrea.transport.api.modulos.financeiro.bancoConta.BancoContaService;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento.RecebimentoLancamentoService;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus.RecebimentoParcelaStatusService;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecebimentoParcelaService {

    @Autowired
    private RecebimentoParcelaRepository recebimentoParcelaRepository;

    @Autowired
    private RecebimentoLancamentoService recebimentoLancamentoService;

    @Autowired
    private RecebimentoParcelaStatusService recebimentoParcelaStatusService;

    @Autowired
    private BancoContaService bancoContaService;

    public RecebimentoParcela salvar(RecebimentoParcela obj) {
        obj = prepararPersistencia(obj);
        return recebimentoParcelaRepository.saveAndFlush(obj);
    }

    public RecebimentoParcela update(String key, RecebimentoParcela obj) {
        return update(descriptografarKey(key), obj);
    }

    public RecebimentoParcela update(Long id, RecebimentoParcela obj) {
        RecebimentoParcela updateFound = buscarPorId(id);
        obj.setId(updateFound.getId());
        obj = prepararPersistencia(obj);
        return recebimentoParcelaRepository.save(obj);
    }

    public RecebimentoParcela buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<RecebimentoParcela> obj = recebimentoParcelaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new RecebimentoParcelaExceptionNaoEncontrado();
    }

    public RecebimentoParcela buscarPorKey(String key) {
        return buscarPorId(descriptografarKey(key));
    }

    private Long descriptografarKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new RecebimentoParcelaExceptionNaoEncontrado();
        }
    }

    private RecebimentoParcela prepararPersistencia(RecebimentoParcela obj) {
        obj.setRecebimentoLancamento(recebimentoLancamentoService.buscarPorObj(obj.getRecebimentoLancamento()));
        obj.setRecebimentoParcelaStatus(recebimentoParcelaStatusService.buscarPorObj(obj.getRecebimentoParcelaStatus()));

        if (obj.getBancoConta() != null) {
            obj.setBancoConta(bancoContaService.buscarPorObj(obj.getBancoConta()));
        }

        return obj;
    }
}
