package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Fretamento;
import br.com.wcorrea.transport.api.model.pessoa.FretamentoTipo;
import br.com.wcorrea.transport.api.repository.fretamento.FretamentoRepository;
import br.com.wcorrea.transport.api.service.exception.FretamentoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class FretamentoService {

    @Autowired
    private FretamentoRepository fretamentoRepository;

    @Autowired
    private PessoaService pessoaService;

    public Fretamento salvar(Fretamento fretamento) {
        if (fretamento.getSituacao().equals(FretamentoTipo.ORCAMENTO)) {
            fretamento.setCliente(null);
        } else {
            fretamento.setCliente(pessoaService.validarPessoa(fretamento.getCliente()));
        }
        return fretamentoRepository.save(fretamento);
    }

    public Fretamento findOne(Long id) {
        Optional<Fretamento> fretamento = fretamentoRepository.findById(id);
        if (!fretamento.isPresent()) {
            throw new FretamentoNaoEncontrado();
        }
        return fretamento.get();
    }


    public Fretamento atualizar(Long id, Fretamento fretamento) {

        //TODO: VERIFICAR A ATUALIZACAO
        return fretamentoRepository.saveAndFlush(fretamento);
    }

    //    public Banco update(Long id, Banco banco) {
//        Banco updateFound = findOne(id);
//        banco.setId(updateFound.getId());
//        banco.setControle(updateFound.getControle());
//        banco.getControle().setDataAlteracao(new Date());
//        return bancoRepository.save(banco);
//    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new FretamentoNaoEncontrado();
        }
    }
}
