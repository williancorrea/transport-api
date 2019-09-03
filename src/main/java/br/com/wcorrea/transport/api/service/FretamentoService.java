package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.FretamentoEventalTipo;
import br.com.wcorrea.transport.api.model.FretamentoEventual;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
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

    @Autowired
    private EstadoCidadeService estadoCidadeService;

    public FretamentoEventual salvar(FretamentoEventual fretamentoEventual) {
        if (fretamentoEventual.getSituacao().equals(FretamentoEventalTipo.ORCAMENTO)) {
            fretamentoEventual.setCliente(null);
        } else {
            fretamentoEventual.setCliente(pessoaService.validarPessoa(fretamentoEventual.getCliente()));
        }

        if (fretamentoEventual.getItinerario().getPartida().after(fretamentoEventual.getItinerario().getRetorno())) {
            throw new RegraDeNegocio("Data de partida n\u00e3o pode ser maior que a data de retorno");
        }

        // VERIFICA SE A CIDADE DE PARTIDA EXISTE
        try {
            Cidade c1 = estadoCidadeService.buscarPorId(fretamentoEventual.getItinerario().getPartidaCidade().getId());
            if (c1 == null) {
                throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
            }
            fretamentoEventual.getItinerario().setPartidaCidade(c1);
        } catch (Exception e) {
            throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
        }


        //VERIFICA SE A CIDADE DE RETORNO EXISTE
        try {
            Cidade c2 = estadoCidadeService.buscarPorId(fretamentoEventual.getItinerario().getRetornoCidade().getId());
            if (c2 == null) {
                throw new RegraDeNegocio("Cidade de retorno n\u00e3o encontrada.");
            }
            fretamentoEventual.getItinerario().setRetornoCidade(c2);
        } catch (Exception e) {
            throw new RegraDeNegocio("Cidade de retorno n\u00e3o encontrada.");
        }


        return fretamentoEventualRepository.save(fretamentoEventual);
    }

    public FretamentoEventual findOne(Long id) {
        if (id != null && id > 0) {
            Optional<FretamentoEventual> obj = fretamentoEventualRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new FretamentoEventualNaoEncontrado();
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
