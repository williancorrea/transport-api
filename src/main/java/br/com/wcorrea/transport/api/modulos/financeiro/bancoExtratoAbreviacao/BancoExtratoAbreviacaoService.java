package br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao;

import br.com.wcorrea.transport.api.modulos.financeiro.banco.BancoService;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoAgencia.BancoAgenciaService;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacao;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacaoExceptionNaoEncontrado;
import br.com.wcorrea.transport.api.modulos.financeiro.bancoExtratoAbreviacao.BancoExtratoAbreviacaoRepository;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BancoExtratoAbreviacaoService {

    @Autowired
    private BancoExtratoAbreviacaoRepository bancoExtratoAbreviacaoRepository;

    @Autowired
    private BancoService bancoService;

    public BancoExtratoAbreviacao salvar(BancoExtratoAbreviacao bancoExtratoAbreviacao) {
        bancoExtratoAbreviacao = prepararObj(bancoExtratoAbreviacao);
        return bancoExtratoAbreviacaoRepository.saveAndFlush(bancoExtratoAbreviacao);
    }

    public BancoExtratoAbreviacao atualizar(Long id, BancoExtratoAbreviacao bancoExtratoAbreviacao) {
        BancoExtratoAbreviacao updateFound = buscarPorId(id);
        bancoExtratoAbreviacao.setId(updateFound.getId());
        bancoExtratoAbreviacao = prepararObj(bancoExtratoAbreviacao);
        return bancoExtratoAbreviacaoRepository.save(bancoExtratoAbreviacao);
    }

    private BancoExtratoAbreviacao prepararObj(BancoExtratoAbreviacao bancoExtratoAbreviacao) {
        bancoExtratoAbreviacao.setBanco(bancoService.buscarPorId(bancoExtratoAbreviacao.getBanco().getId()));
        return bancoExtratoAbreviacao;
    }

    public BancoExtratoAbreviacao buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<BancoExtratoAbreviacao> obj = bancoExtratoAbreviacaoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new BancoExtratoAbreviacaoExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new BancoExtratoAbreviacaoExceptionNaoEncontrado();
        }
    }
}
