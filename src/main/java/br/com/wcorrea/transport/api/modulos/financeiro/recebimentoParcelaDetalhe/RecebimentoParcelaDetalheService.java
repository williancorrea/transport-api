package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaDetalhe;

import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecebimentoParcelaDetalheService {

    @Autowired
    private RecebimentoParcelaDetalheRepository recebimentoParcelaDetalheRepository;

    public RecebimentoParcelaDetalhe salvar(RecebimentoParcelaDetalhe obj) {
        obj = prepararPersistencia(obj);
        return recebimentoParcelaDetalheRepository.saveAndFlush(obj);
    }

    public RecebimentoParcelaDetalhe update(String key, RecebimentoParcelaDetalhe obj) {
        return update(descriptografarKey(key), obj);
    }

    public RecebimentoParcelaDetalhe update(Long id, RecebimentoParcelaDetalhe obj) {
        RecebimentoParcelaDetalhe updateFound = buscarPorId(id);
        obj.setId(updateFound.getId());
        obj = prepararPersistencia(obj);
        return recebimentoParcelaDetalheRepository.save(obj);
    }

    public RecebimentoParcelaDetalhe buscarPorObj(RecebimentoParcelaDetalhe obj) {
        return buscarPorId(obj.getId());
    }

    public RecebimentoParcelaDetalhe buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<RecebimentoParcelaDetalhe> obj = recebimentoParcelaDetalheRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new RecebimentoParcelaDetalheExceptionNaoEncontrado();
    }

    public RecebimentoParcelaDetalhe buscarPorKey(String key) {
        return buscarPorId(descriptografarKey(key));
    }

    private Long descriptografarKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new RecebimentoParcelaDetalheExceptionNaoEncontrado();
        }
    }

    private RecebimentoParcelaDetalhe prepararPersistencia(RecebimentoParcelaDetalhe obj) {

        return obj;
    }
}
