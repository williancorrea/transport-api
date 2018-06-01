package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.repository.PessoaRepository;
import br.com.wcorrea.transport.api.service.exception.*;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Classe responsavel por manipular as regras de negocio de pessoa
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EstadoCivilRepository maritalStatusRepository;

    public Pessoa save(Pessoa pessoaNovo) {
        pessoaNovo.setId(null);
        pessoaNovo.getPessoaFisica().setId(null);

        pessoaNovo = this.validarPessoa(pessoaNovo);
        return pessoaRepository.saveAndFlush(pessoaNovo);
    }

    /**
     * Atualiza o objeto do tipo pessoa (Fisica / Jurídica)
     *
     * @param id
     * @param pessoa
     * @return
     */
    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa objFound = findOne(id);

        pessoa.setId(objFound.getId());
        pessoa.setPropriedades(objFound.getPropriedades());
        pessoa.getPessoaFisica().setId(objFound.getPessoaFisica().getId());

        pessoa = this.validarPessoa(pessoa);
//        BeanUtils.copyProperties(pessoa, objFound, "id", "pessoaFisica", "propriedades");
//        BeanUtils.copyProperties(pessoa.getPessoaFisica(), objFound.getPessoaFisica(),"id");

//        pessoa.setPropriedades(objFound.getPropriedades());
//        pessoa.getPropriedades().setModificationDate(LocalDateTime.now());
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

    /**
     * Efetua a validação do objeto Pessoa
     *
     * @param pessoa
     * @return
     */
    private Pessoa validarPessoa(Pessoa pessoa) {
        Pessoa pessoaEncontrada = null;
        if (pessoa.isPessoaFisica()) {
            /**
             * GARANTE QUE OS DADOS DE PESSOA FISICA EXITEM
             */
            if (pessoa.getPessoaFisica() == null) {
                throw new PessoaFisicaNaoEncontrada();
            }

            if(pessoa.getId() == null && pessoa.getPessoaFisica().getId() == null) {
                if (this.findOneByCPF(pessoa.getPessoaFisica().getCpf()) != null) {
                    throw new PessoaFisicaJaCadastrada();
                }
            }else{
                //NÃO SEI SE LAVO OU SE COZINHO
            }

            if (Utils.validarCPF(pessoa.getPessoaFisica().getCpf()) == false) {
                throw new PessoaFisicaCPFInvalido();
            }

            /**
             * VERIFICA SE O ESTADO CIVIL EXISTE NA BASE DE DADOS
             */
            try {
                EstadoCivil estadoCivilEncontrado = maritalStatusRepository.findOne(pessoa.getPessoaFisica().getEstadoCivil().getId());
                if (estadoCivilEncontrado == null) {
                    throw new EstadoCivilNaoEncontrado();
                }
                pessoa.getPessoaFisica().setEstadoCivil(estadoCivilEncontrado);
            }catch (Exception e){
                throw new EstadoCivilNaoEncontrado();
            }

            pessoa.getPessoaFisica().setPessoa(pessoa);
        } else {
            //TODO: VALIDAR DADOS DE PESSOA JURIDICA
            //TODO: VERIFICAR SE O CNPJ JÁ EXITE
        }

        return pessoa;
    }



}
