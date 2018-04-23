package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Person;
import br.com.wcorrea.transport.api.repository.PersonRepository;
import br.com.wcorrea.transport.api.service.exception.PersonNotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Classe responsavel por manipular as regras de negocio de pessoa
 */
@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    /**
     * Atualiza o objeto do tipo person (Fisica / Jurídica)
     *
     * @param id
     * @param person
     * @return
     */
    public Person update(Long id, Person person) {
        Person objFound = personRepository.save(findOne(id));
        person.setId(objFound.getId());
        person.setProperties(objFound.getProperties());
        person.getProperties().setModificationDate(LocalDateTime.now());
        return personRepository.save(person);
    }

    /**
     * Encontar pessoa por ID
     */
    private Person findOne(Long id) {
        Person person = personRepository.findOne(id);
        if (person == null) {
            throw new PersonNotFound();
        }
        return person;
    }
}
