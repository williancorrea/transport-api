package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

import br.com.wcorrea.transport.api.utils.Criptografia;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecebimentoLancamentoService {

    @Autowired
    private RecebimentoLancamentoRepository recebimentoLancamentoRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private PlanoContaService planoContaService;

    @Autowired
    private PlanoContaService planoContaService;

    private FretamentoEventualService fretamentoEventualService;

    public RecebimentoLancamento salvar(RecebimentoLancamento r) {
        r = prepararPersistenciaDados(r);
        return recebimentoLancamentoRepository.saveAndFlush(r);
    }

    public RecebimentoLancamento update(Long id, RecebimentoLancamento obj) {
        RecebimentoLancamento updateFound = buscarPorId(id);
        obj.setId(updateFound.getId());
        obj = prepararPersistenciaDados(obj);
        return recebimentoLancamentoRepository.save(obj);
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
