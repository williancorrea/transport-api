package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ClasseDespesa;
import br.com.wcorrea.transport.api.repository.classeDespesa.ClasseDespesaRepository;
import br.com.wcorrea.transport.api.service.exception.ClasseDespesaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ClasseDespesa responsavel por manipular todas as informcoes do objeto classeDespesa
 */
@Service
public class ClasseDespesaService {

    @Autowired
    private ClasseDespesaRepository classeDespesaRepository;

    /**
     * Atualizar
     *
     * @param id
     * @param classeDespesa
     * @return
     */
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

    /**
     * Buscar por ID
     */
    public ClasseDespesa buscarPorId(Long id) {
        ClasseDespesa classeDespesa = classeDespesaRepository.findOne(id);
        if (classeDespesa == null) {
            throw new ClasseDespesaNaoEncontrada();
        }
        return classeDespesa;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new ClasseDespesaNaoEncontrada();
        }
    }

}
