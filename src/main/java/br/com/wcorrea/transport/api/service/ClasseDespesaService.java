package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ClasseDespesa;
import br.com.wcorrea.transport.api.repository.financeiro.classeDespesa.ClasseDespesaRepository;
import br.com.wcorrea.transport.api.service.exception.ClasseDespesaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClasseDespesaService {

    @Autowired
    private ClasseDespesaRepository classeDespesaRepository;

    public ClasseDespesa atualizar(Long id, ClasseDespesa classeDespesa) {
        ClasseDespesa objFound = classeDespesaRepository.save(buscarPorId(id));
        classeDespesa.setId(objFound.getId());

        return salvar(classeDespesa);
    }

    public ClasseDespesa salvar(ClasseDespesa classeDespesa) {
        return classeDespesaRepository.saveAndFlush(classeDespesa);
    }

    public ClasseDespesa buscarPorId(Long id) {
        if (id != null && id > 0) {
            Optional<ClasseDespesa> obj = classeDespesaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new ClasseDespesaNaoEncontrada();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new ClasseDespesaNaoEncontrada();
        }
    }

}
