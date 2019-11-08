package br.com.wcorrea.transport.api.modulos.financeiro.planoConta;

import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlanoContaService {

    @Autowired
    private PlanoContaRepository planoContaRepository;

    public PlanoConta salvar(PlanoConta r) {
        return planoContaRepository.saveAndFlush(r);
    }

    public PlanoConta update(Long id, PlanoConta banco) {
        PlanoConta updateFound = buscarPorId(id);
        banco.setId(updateFound.getId());
        return planoContaRepository.save(banco);
    }

    public PlanoConta buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<PlanoConta> obj = planoContaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new PlanoContaExceptionNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new PlanoContaExceptionNaoEncontrado();
        }
    }
}
