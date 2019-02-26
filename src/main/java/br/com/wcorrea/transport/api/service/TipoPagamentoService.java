package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.TipoPagamento;
import br.com.wcorrea.transport.api.repository.tipoPagamento.TipoPagamentoRepository;
import br.com.wcorrea.transport.api.service.exception.ClasseDespesaNaoEncontrada;
import br.com.wcorrea.transport.api.service.exception.TipoPagamentoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * TipoPagamento responsavel por manipular todas as informcoes do objeto tipoPagamento
 */
@Service
public class TipoPagamentoService {

    @Autowired
    private TipoPagamentoRepository tipoPagamentoRepository;

    /**
     * Atualizar
     *
     * @param id
     * @param tipoPagamento
     * @return
     */
    public TipoPagamento atualizar(Long id, TipoPagamento tipoPagamento) {
        TipoPagamento objFound = tipoPagamentoRepository.save(buscarPorId(id));
        tipoPagamento.setId(objFound.getId());

        tipoPagamento.setControle(objFound.getControle());
        tipoPagamento.getControle().setDataAlteracao(new Date());

        return salvar(tipoPagamento);
    }

    public TipoPagamento salvar(TipoPagamento tipoPagamento) {
        return tipoPagamentoRepository.saveAndFlush(tipoPagamento);
    }

    /**
     * Buscar por ID
     */
    public TipoPagamento buscarPorId(Long id) {
        TipoPagamento tipoPagamento = tipoPagamentoRepository.findOne(id);
        if (tipoPagamento == null) {
            throw new TipoPagamentoNaoEncontrado();
        }
        return tipoPagamento;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new TipoPagamentoNaoEncontrado();
        }
    }

}
