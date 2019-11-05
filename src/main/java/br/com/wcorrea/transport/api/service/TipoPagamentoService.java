package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.financeiro.TipoPagamento;
import br.com.wcorrea.transport.api.repository.financeiro.tipoPagamento.TipoPagamentoRepository;
import br.com.wcorrea.transport.api.service.exception.TipoPagamentoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TipoPagamentoService {

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    public TipoPagamento atualizar(Long id, TipoPagamento tipoPagamento) {
        TipoPagamento objFound = tipoPagamentoRepository.save(buscarPorId(id));
        tipoPagamento.setId(objFound.getId());

        return salvar(tipoPagamento);
    }

    public TipoPagamento salvar(TipoPagamento tipoPagamento) {
        return tipoPagamentoRepository.saveAndFlush(tipoPagamento);
    }

    public TipoPagamento buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<TipoPagamento> obj = tipoPagamentoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new TipoPagamentoNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new TipoPagamentoNaoEncontrado();
        }
    }

}
