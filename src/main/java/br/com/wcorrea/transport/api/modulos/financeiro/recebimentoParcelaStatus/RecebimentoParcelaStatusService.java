package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus;

import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecebimentoParcelaStatusService {

    @Autowired
    private RecebimentoParcelaStatusRepository recebimentoParcelaStatusRepository;

    public RecebimentoParcelaStatus salvar(RecebimentoParcelaStatus r) {
        return recebimentoParcelaStatusRepository.saveAndFlush(r);
    }

    public RecebimentoParcelaStatus update(Long id, RecebimentoParcelaStatus banco) {
        RecebimentoParcelaStatus updateFound = buscarPorId(id);
        banco.setId(updateFound.getId());
        return recebimentoParcelaStatusRepository.save(banco);
    }

    public RecebimentoParcelaStatus buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<RecebimentoParcelaStatus> obj = recebimentoParcelaStatusRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new RecebimentoParcelaStatusExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new RecebimentoParcelaStatusExceptionNaoEncontrado();
        }
    }
}
