package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.ClasseDespeza;
import br.com.wcorrea.transport.api.repository.classeDespeza.ClasseDespezaDespezaRepository;
import br.com.wcorrea.transport.api.service.exception.ClasseDespezaNaoEncontrada;
import br.com.wcorrea.transport.api.service.exception.veiculo.ItinerarioNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * ClasseDespeza responsavel por manipular todas as informcoes do objeto classeDespeza
 */
@Service
public class ClasseDespezaService {

    @Autowired
    private ClasseDespezaDespezaRepository classeDespezaRepository;

    /**
     * Atualizar
     *
     * @param id
     * @param classeDespeza
     * @return
     */
    public ClasseDespeza atualizar(Long id, ClasseDespeza classeDespeza) {
        ClasseDespeza objFound = classeDespezaRepository.save(buscarPorId(id));
        classeDespeza.setId(objFound.getId());

        classeDespeza.setControle(objFound.getControle());
        classeDespeza.getControle().setDataAlteracao(new Date());

        return salvar(classeDespeza);
    }

    public ClasseDespeza salvar(ClasseDespeza classeDespeza) {
        return classeDespezaRepository.saveAndFlush(classeDespeza);
    }

    /**
     * Buscar por ID
     */
    public ClasseDespeza buscarPorId(Long id) {
        ClasseDespeza classeDespeza = classeDespezaRepository.findOne(id);
        if (classeDespeza == null) {
            throw new ClasseDespezaNaoEncontrada();
        }
        return classeDespeza;
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new ClasseDespezaNaoEncontrada();
        }
    }

}
