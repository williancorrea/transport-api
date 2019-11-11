package br.com.wcorrea.transport.api.modulos.financeiro.chequeRecebido;

import br.com.wcorrea.transport.api.service.PessoaService;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChequeRecebidoService {

    @Autowired
    private ChequeRecebidoRepository chequeRecebidoRepository;

    @Autowired
    private PessoaService pessoaService;

    public ChequeRecebido salvar(ChequeRecebido r) {
        r = prepararObj(r);
        return chequeRecebidoRepository.saveAndFlush(r);
    }

    public ChequeRecebido update(Long id, ChequeRecebido obj) {
        ChequeRecebido updateFound = buscarPorId(id);
        obj.setId(updateFound.getId());
        obj = prepararObj(obj);
        return chequeRecebidoRepository.save(obj);
    }

    public ChequeRecebido buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<ChequeRecebido> obj = chequeRecebidoRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new ChequeRecebidoExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return buscarPorId((Long) new Criptografia().getKey(key)).getId();
        } catch (Exception e) {
            throw new ChequeRecebidoExceptionNaoEncontrado();
        }
    }

    private ChequeRecebido prepararObj(ChequeRecebido obj) {
        obj.setCliente(pessoaService.buscarPorId(obj.getId()));
        return obj;
    }
}
