package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.MaritalStatus;
import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.model.common.CommonProperties;
import br.com.wcorrea.transport.api.repository.MaritalStatusRepository;
import br.com.wcorrea.transport.api.repository.PessoaRepository;
import br.com.wcorrea.transport.api.service.exception.MaritalStatusNotFound;
import br.com.wcorrea.transport.api.service.exception.PessoaFisicaJaCadastrada;
import br.com.wcorrea.transport.api.service.exception.PessoaFisicaNaoEncontrada;
import br.com.wcorrea.transport.api.service.exception.PessoaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Cryptography;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * Classe responsavel por manipular as regras de negocio de pessoa
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private MaritalStatusRepository maritalStatusRepository;

    public Pessoa save(Pessoa pessoaNovo) {

        pessoaNovo = this.validarPessoa(pessoaNovo);
        return pessoaRepository.saveAndFlush(pessoaNovo);
    }

    /**
     * Efetua a validação do objeto Pessoa
     *
     * @param pessoa
     * @return
     */
    private Pessoa validarPessoa(Pessoa pessoa) {

        if (pessoa.isPessoaFisica()) {
            /**
             * GARANTE QUE OS DADOS DE PESSOA FISICA EXITEM
             */
            if (pessoa.getPessoaFisica() == null) {
                throw new PessoaFisicaNaoEncontrada();
            }

            /**
             * GARANTE QUE NAO TERA NENHUM CPF DUPLICADO NA BASE DE DADOS
             */
            //TODO: NAO ESTA FUNCIONANDO
            if (this.findOneByCPF(pessoa.getPessoaFisica().getCpf()) != null) {
                throw new PessoaFisicaJaCadastrada();
            }

            /**
             * VERIFICA SE O ESTADO CIVIL EXISTE NA BASE DE DADOS
             */
            try {
                Long id = Long.parseLong(new Cryptography().decryptFromHex(pessoa.getPessoaFisica().getMaritalStatus().getId().toString()));
                MaritalStatus estadoCivilEncontrado = maritalStatusRepository.findOne(id);
                if (estadoCivilEncontrado == null) {
                    throw new MaritalStatusNotFound();
                }
                pessoa.getPessoaFisica().setMaritalStatus(estadoCivilEncontrado);
            } catch (Exception e) {
                throw new MaritalStatusNotFound();
            }


            pessoa.getPessoaFisica().setPessoa(pessoa);
        } else {
            //TODO: VALIDAR DADOS DE PESSOA JURIDICA
            //TODO: VERIFICAR SE O CNPJ JÁ EXITE
        }

        return pessoa;
    }

    /**
     * Atualiza o objeto do tipo pessoa (Fisica / Jurídica)
     *
     * @param id
     * @param pessoa
     * @return
     */
    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa objFound = pessoaRepository.save(findOne(id));
        pessoa.setId(objFound.getId());
        pessoa.setPropriedades(objFound.getPropriedades());
        pessoa.getPropriedades().setModificationDate(LocalDateTime.now());
        return pessoaRepository.save(pessoa);
    }

    /**
     * Encontar pessoa por ID
     */
    public Pessoa findOne(Long id) {
        Pessoa pessoa = pessoaRepository.findOne(id);
        if (pessoa == null) {
            throw new PessoaNaoEncontrada();
        }
        return pessoa;
    }

    public Pessoa findOneByCPF(String cpf) {
        return pessoaRepository.findOneByCPF(cpf);
    }

}
