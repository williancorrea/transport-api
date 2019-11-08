package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoTipo;

import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecebimentoTipoService {

    @Autowired
    private RecebimentoTipoRepository recebimentoTipoRepository;

    public RecebimentoTipo salvar(RecebimentoTipo r) {
        return recebimentoTipoRepository.saveAndFlush(r);
    }

    public RecebimentoTipo update(Long id, RecebimentoTipo banco) {
        RecebimentoTipo updateFound = buscarPorId(id);
        banco.setId(updateFound.getId());
        return recebimentoTipoRepository.save(banco);
    }

    public RecebimentoTipo buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<RecebimentoTipo> obj = recebimentoTipoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new RecebimentoTipoExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new RecebimentoTipoExceptionNaoEncontrado();
        }
    }
}
