package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Classe;
import br.com.wcorrea.transport.api.repository.classe.ClasseRepository;
import br.com.wcorrea.transport.api.service.exception.ClasseNaoEncontrada;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Classe responsavel por manipular todas as informcoes do objeto classe
 */
@Service
public class ClasseService {

    @Autowired
    private ClasseRepository classeRepository;

    /**
     * Atualizar
     *
     * @param id
     * @param classe
     * @return
     */
    public Classe atualizar(Long id, Classe classe) {
        Classe objFound = classeRepository.save(buscarPorId(id));
        classe.setId(objFound.getId());

        classe.setControle(objFound.getControle());
        classe.getControle().setDataAlteracao(new Date());

        return salvar(classe);
    }

    public Classe salvar(Classe classe) {
        return classeRepository.saveAndFlush(classe);
    }

    /**
     * Buscar por ID
     */
    public Classe buscarPorId(Long id) {
        Classe classe = classeRepository.findOne(id);
        if (classe == null) {
            throw new ClasseNaoEncontrada();
        }
        return classe;
    }

}
