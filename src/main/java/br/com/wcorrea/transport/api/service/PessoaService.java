package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.model.PessoaAuditoria;
import br.com.wcorrea.transport.api.model.PessoaTipo;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaRepository;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.service.exception.*;
import br.com.wcorrea.transport.api.utils.Criptografia;
import br.com.wcorrea.transport.api.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

/**
 * ClasseDespesa responsavel por manipular as regras de negocio de pessoa
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    public Pessoa save(Pessoa pessoaNovo) {
        pessoaNovo.setId(null);
        pessoaNovo = validarPessoa(pessoaNovo);
        return pessoaRepository.saveAndFlush(pessoaNovo);
    }

    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa objFound = buscarPorId(id);

        pessoa.setId(objFound.getId());
        pessoa.setControle(objFound.getControle());

        if (objFound.getTipo().equals(PessoaTipo.FISICA)) {
            pessoa.getPessoaFisica().setId(objFound.getPessoaFisica().getId());
            pessoa.setPessoaJuridica(null);
            pessoa = this.validarPessoaFisica(pessoa);
        } else {
            pessoa.getPessoaJuridica().setId(objFound.getPessoaJuridica().getId());
            pessoa.setPessoaFisica(null);
            pessoa = this.validarPessoaJuridica(pessoa);
        }

        pessoa = validarPessoa(pessoa);

//        BeanUtils.copyProperties(pessoa, objFound, "id", "pessoaFisica", "propriedades");
//        BeanUtils.copyProperties(pessoa.getPessoaFisica(), objFound.getPessoaFisica(),"id");

//        pessoa.setPropriedades(objFound.getPropriedades());
//        pessoa.getPropriedades().setDataAlteracao(LocalDateTime.now());
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPorId(Long id) {
        Pessoa pessoaEncontrada = pessoaRepository.findOne(id);
        if (pessoaEncontrada == null) {
            throw new PessoaNaoEncontrada();
        }
        return pessoaEncontrada;
    }

    public Pessoa buscarPorId(Pessoa pessoa) {
        if (pessoa == null) {
            throw new PessoaNaoEncontrada();
        }
        return this.buscarPorId(pessoa.getId());
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new PessoaNaoEncontrada();
        }
    }

    public Pessoa findOneByCPF(String cpf) {
        return pessoaRepository.findOneByCPF(cpf);
    }

    public Pessoa findOneByCNPJ(String cnpj) {
        return pessoaRepository.findOneByCNPJ(cnpj);
    }

    private Pessoa validarPessoa(Pessoa pessoa) {
        Pessoa pessoaEncontrada = pessoa.getId() != null ? this.buscarPorId(pessoa.getId()) : null;


        if (pessoa.getTipo().equals(PessoaTipo.FISICA)) {
            if (pessoa.getId() == null) {
                pessoa.getPessoaFisica().setId(null);
            }
            pessoa = this.validarPessoaFisica(pessoa);
        } else {
            if (pessoa.getId() == null) {
                pessoa.getPessoaJuridica().setId(null);
            }
            pessoa = this.validarPessoaJuridica(pessoa);
        }

        /**
         * IMPEDE A ALTERACAO DO CPF
         */
        if (pessoa.getPessoaFisica() != null && !pessoa.getPessoaFisica().getCpf().equals(pessoaEncontrada.getPessoaFisica().getCpf())) {
            throw new PessoaFisicaNaoPodeAlterarValorCPF();
        }
        /**
         * IMPEDE A ALTERACAO DO CNPJ
         */
        if (pessoa.getPessoaJuridica() != null && !pessoa.getPessoaJuridica().getCnpj().equals(pessoaEncontrada.getPessoaJuridica().getCnpj())) {
            throw new PessoaJuridicaNaoPodeAlterarValorCNPJ();
        }

        if (pessoa.getId() == null) {
            Pessoa finalPessoa = pessoa;
            pessoa.getListaPessoaTelefone().forEach(c -> c.setPessoa(finalPessoa));
            pessoa.getListaPessoaContato().forEach(c -> c.setPessoa(finalPessoa));
            pessoa.getListaPessoaEndereco().forEach(c -> c.setPessoa(finalPessoa));
        } else {
            /**
             * ADICIONANDO A LISTA DE TELEFONES
             */
            pessoaEncontrada.getListaPessoaTelefone().clear();
            pessoaEncontrada.getListaPessoaTelefone().addAll(pessoa.getListaPessoaTelefone());
            pessoaEncontrada.getListaPessoaTelefone().forEach(c -> c.setPessoa(pessoaEncontrada));
            /**
             * ADICIONANDO A LISTA DE CONTATOS
             */
            pessoaEncontrada.getListaPessoaContato().clear();
            pessoaEncontrada.getListaPessoaContato().addAll(pessoa.getListaPessoaContato());
            pessoaEncontrada.getListaPessoaContato().forEach(c -> c.setPessoa(pessoaEncontrada));
            /**
             * ADICIONANDO A LISTA DE ENDEREÇOS
             */
            pessoaEncontrada.getListaPessoaEndereco().clear();
            pessoaEncontrada.getListaPessoaEndereco().addAll(pessoa.getListaPessoaEndereco());
            pessoaEncontrada.getListaPessoaEndereco().forEach(c -> c.setPessoa(pessoaEncontrada));

            BeanUtils.copyProperties(pessoa, pessoaEncontrada, "id", "listaPessoaTelefone", "listaPessoaContato", "listaPessoaEndereco");
        }
        /**
         * CONSTROI O OBJETO DE AUDITORIA
         */
        pessoa.getListaPessoaAuditoria().add(construirObjAuditoria(pessoa, pessoaEncontrada));
        return pessoa;
    }

    private Pessoa validarPessoaFisica(Pessoa pessoa) {
        /**
         * GARANTE QUE OS DADOS DE PESSOA FISICA EXITEM
         */
        if (pessoa.getPessoaFisica() == null) {
            throw new PessoaFisicaNaoEncontrada();
        }
        /**
         * GARANTE UM CPF VALIDO
         */
        if (Utils.validarCPF(pessoa.getPessoaFisica().getCpf()) == false) {
            throw new PessoaFisicaCPFInvalido();
        }
        /**
         * GARANTE UM CPF UNICO
         */
        if (this.pessoaRepository.verificarCPFJaCadastrado(pessoa.getPessoaFisica().getCpf(), pessoa.getId())) {
            throw new PessoaFisicaJaCadastrada();
        }
        /**
         * VERIFICA SE O ESTADO CIVIL EXISTE NA BASE DE DADOS
         */
        if (pessoa.getPessoaFisica().getEstadoCivil() != null && pessoa.getPessoaFisica().getId() != null) {
            try {
                EstadoCivil estadoCivilEncontrado = estadoCivilRepository.findOne(pessoa.getPessoaFisica().getEstadoCivil().getId());
                if (estadoCivilEncontrado == null) {
                    throw new EstadoCivilNaoEncontrado();
                }
                pessoa.getPessoaFisica().setEstadoCivil(estadoCivilEncontrado);
            } catch (Exception e) {
                throw new EstadoCivilNaoEncontrado();
            }
        } else {
            pessoa.getPessoaFisica().setEstadoCivil(null);
        }

        pessoa.getPessoaFisica().setPessoa(pessoa);
        return pessoa;
    }

    public Pessoa validarPessoaJuridica(Pessoa pessoa) {
        /**
         * GARANTE QUE OS DADOS DE PESSOA JURIDICA EXISTE
         */
        if (pessoa.getPessoaJuridica() == null) {
            throw new PessoaJuridicaNaoEncontrada();
        }
        /**
         * GARANTE UM CNPJ VALIDO
         */
        if (this.pessoaRepository.verificarCNPJJaCadastrado(pessoa.getPessoaJuridica().getCnpj(), pessoa.getId())) {
            throw new PessoaJuridicaCNPJInvalido();
        }
        /**
         * GARANTE UM CPNJ UNICO
         */
        if (this.findOneByCNPJ(pessoa.getPessoaJuridica().getCnpj()) != null) {
            throw new PessoaJuridicaJaCadastrada();
        }
        pessoa.getPessoaJuridica().setPessoa(pessoa);

        return pessoa;

    }

    private PessoaAuditoria construirObjAuditoria(Pessoa novo, Pessoa antigo) {
        PessoaAuditoria aud = new PessoaAuditoria();
        aud.setDataAlteracao(new Date());
        aud.setPessoa(novo);

        StringBuilder txt = new StringBuilder();

        //TODO: CONSTRUIR OBJETO DE AUDITORIA
        if (novo.getPessoaFisica() != null) {
            txt.append("\nCPF: " + novo.getPessoaFisica().getCpf());
        } else {
            txt.append("\nCNPJ: " + novo.getPessoaJuridica().getCnpj());
        }
        txt.append("\nNOME: " + novo.getNome());

        //TODO: Adicionar usuario logado que fez a alteração
        aud.setObjetoAlterado(txt.toString());

        return aud;
    }
}
