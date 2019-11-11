package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecebimentoLancamentoService {

    @Autowired
    private RecebimentoLancamentoRepository recebimentoLancamentoRepository;

    public RecebimentoLancamento salvar(RecebimentoLancamento r) {
        return recebimentoLancamentoRepository.saveAndFlush(r);
    }

    public RecebimentoLancamento update(Long id, RecebimentoLancamento banco) {
        RecebimentoLancamento updateFound = buscarPorId(id);
        banco.setId(updateFound.getId());
        return recebimentoLancamentoRepository.save(banco);
    }

    public RecebimentoLancamento buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<RecebimentoLancamento> obj = recebimentoLancamentoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new RecebimentoLancamentoExceptionNaoEncontrado();
    }

    public RecebimentoLancamento buscarPorKey(String key) {
        try {
            return buscarPorId(new Criptografia().getKey(key));
        } catch (Exception e) {
            throw new RecebimentoLancamentoExceptionNaoEncontrado();
        }
    }
}
