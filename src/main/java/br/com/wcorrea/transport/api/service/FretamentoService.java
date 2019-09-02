package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.FretamentoEventalTipo;
import br.com.wcorrea.transport.api.model.FretamentoEventual;
import br.com.wcorrea.transport.api.repository.fretamentoEventual.FretamentoEventualEventualRepository;
import br.com.wcorrea.transport.api.service.exception.FretamentoEventualNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.RegraDeNegocio;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FretamentoService {

    @Autowired
    private FretamentoEventualEventualRepository fretamentoEventualRepository;

    @Autowired
    private PessoaService pessoaService;

    public FretamentoEventual salvar(FretamentoEventual fretamentoEventual) {
        if (fretamentoEventual.getSituacao().equals(FretamentoEventalTipo.ORCAMENTO)) {
            fretamentoEventual.setCliente(null);
        } else {
            fretamentoEventual.setCliente(pessoaService.validarPessoa(fretamentoEventual.getCliente()));
        }

        if (true) {
            throw new RegraDeNegocio("Deu merda em alguma coisa ae!");
        }

        // TODO: VALIDAR DATA DE PARTIDA
        // TODO: CIDADE DE PARTIDA
        // TODO: VALIDAR DATA DE RETORNO
        // TODO: CIDADE DE RETORNO
        // TODO: VALIDAR PERIODO ENTRE AS DATA DE PARTIDA E RETORNO

        return fretamentoEventualRepository.save(fretamentoEventual);
    }

    public FretamentoEventual findOne(Long id) {
        Optional<FretamentoEventual> fretamento = fretamentoEventualRepository.findById(id);
        if (!fretamento.isPresent()) {
            throw new FretamentoEventualNaoEncontrado();
        }
        return fretamento.get();
    }


    public FretamentoEventual atualizar(Long id, FretamentoEventual fretamentoEventual) {

        //TODO: VERIFICAR A ATUALIZACAO
        return fretamentoEventualRepository.saveAndFlush(fretamentoEventual);
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
            throw new FretamentoEventualNaoEncontrado();
        }
    }
}
