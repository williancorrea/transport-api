package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ClasseDespesa;
import br.com.wcorrea.transport.api.repository.classeDespesa.ClasseDespesaRepository;
import br.com.wcorrea.transport.api.service.exception.ClasseDespesaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ClasseDespesaService {

    @Autowired
    private ClasseDespesaRepository classeDespesaRepository;

    public ClasseDespesa atualizar(Long id, ClasseDespesa classeDespesa) {
        ClasseDespesa objFound = classeDespesaRepository.save(buscarPorId(id));
        classeDespesa.setId(objFound.getId());

        classeDespesa.setControle(objFound.getControle());
        classeDespesa.getControle().setDataAlteracao(new Date());

        return salvar(classeDespesa);
    }

    public ClasseDespesa salvar(ClasseDespesa classeDespesa) {
        return classeDespesaRepository.saveAndFlush(classeDespesa);
    }

    public ClasseDespesa buscarPorId(Long id) {
        Optional<ClasseDespesa> classeDespesa = classeDespesaRepository.findById(id);
        if (!classeDespesa.isPresent()) {
            throw new ClasseDespesaNaoEncontrada();
        }
        return classeDespesa.get();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new ClasseDespesaNaoEncontrada();
        }
    }

}
