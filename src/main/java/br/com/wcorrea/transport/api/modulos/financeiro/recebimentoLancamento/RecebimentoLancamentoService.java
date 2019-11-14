package br.com.wcorrea.transport.api.modulos.financeiro.recebimentoLancamento;

import br.com.wcorrea.transport.api.modulos.financeiro.DocumentoOrigemTipo;
import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventual;
import br.com.wcorrea.transport.api.modulos.financeiro.planoConta.PlanoContaService;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcela.RecebimentoParcela;
import br.com.wcorrea.transport.api.modulos.financeiro.recebimentoParcelaStatus.RecebimentoParcelaStatusService;
import br.com.wcorrea.transport.api.service.FretamentoEventualService;
import br.com.wcorrea.transport.api.service.PessoaService;
import br.com.wcorrea.transport.api.service.exception.RegraDeNegocio;
import br.com.wcorrea.transport.api.utils.Criptografia;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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
    private FretamentoEventualService fretamentoEventualService;

    @Autowired
    private RecebimentoParcelaStatusService recebimentoParcelaStatusService;

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

    private RecebimentoLancamento prepararPersistenciaDados(RecebimentoLancamento obj) {
        try {
            obj.setCliente(pessoaService.buscarPorId(obj.getCliente().getId()));
        } catch (Exception e) {
            throw new RegraDeNegocio("Cliente n\u00e3o encontrado");
        }
        try {
            obj.setVendedor(pessoaService.buscarPorId(obj.getVendedor().getId()));
        } catch (Exception e) {
            throw new RegraDeNegocio("Vendedor/Representante n\u00e3o encontrado.");
        }
        obj.setPlanoConta(planoContaService.buscarPorId(obj.getPlanoConta().getId()));


        if (obj.getDocumentoOrigem().getTipo().equals(DocumentoOrigemTipo.FRETAMENTO_EVENTUAL)) {
            FretamentoEventual f = fretamentoEventualService.buscarPorId(obj.getDocumentoOrigem().getFretamentoEventual().getId());

            if(f.isContratado() == false){
                throw new RegraDeNegocio("Fretamento Eventual ("+ Utils.StrZeroEsquerda(f.getId().toString(), 5)+") selecionado n\u00e3o pode ser utilizado pois n\u00e3o est\u00e1 com o status de CONTRATADO.");
            }
            obj.getDocumentoOrigem().setFretamentoEventual(f);
            obj.getDocumentoOrigem().setDescricao("Fretamento Eventual (" + Utils.StrZeroEsquerda(f.getId().toString(), 5) + ") - "+ f.getCliente().getNome()   );
        }

        List<RecebimentoParcela> listaParcela = new ArrayList<>();
        for (RecebimentoParcela p: obj.getRecebimentoParcelaList()) {
            p.setRecebimentoParcelaStatus(recebimentoParcelaStatusService.buscarPorId(p.getId()));
            p.setRecebimentoLancamento(obj);
        }
        obj.setRecebimentoParcelaList(listaParcela);


        // TODO: fazer um cadastro a parte das comissoes a ser  realizadas
//        obj.setComissaoTaxa();
//        obj.setComissaoValor();

        return obj;
    }
}
