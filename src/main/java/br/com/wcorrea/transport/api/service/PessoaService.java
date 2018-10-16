package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.EstadoCivil;
import br.com.wcorrea.transport.api.model.Pessoa;
import br.com.wcorrea.transport.api.model.PessoaTipo;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaRepository;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.service.exception.*;
import br.com.wcorrea.transport.api.service.exception.veiculo.ItinerarioNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClasseDespesa responsavel por manipular as regras de negocio de pessoa
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EstadoCivilRepository maritalStatusRepository;

    public Pessoa save(Pessoa pessoaNovo) {
        pessoaNovo.setId(null);
        if (pessoaNovo.getTipo().equals(PessoaTipo.FISICA)) {
            pessoaNovo.getPessoaFisica().setId(null);
            pessoaNovo = this.validarPessoaFisica(pessoaNovo);
        } else {
            pessoaNovo.getPessoaJuridica().setId(null);
            pessoaNovo = this.validarPessoaJuridica(pessoaNovo);
        }
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

    private Pessoa validarPessoaFisica(Pessoa pessoa) {
        /**
         * GARANTE QUE OS DADOS DE PESSOA FISICA EXITEM
         */
        if (pessoa.getPessoaFisica() == null) {
            throw new PessoaFisicaNaoEncontrada();
        }

        if (Utils.validarCPF(pessoa.getPessoaFisica().getCpf()) == false) {
            throw new PessoaFisicaCPFInvalido();
        }

        Long pessoaFisicaId = this.findOneByCPF(pessoa.getPessoaFisica().getCpf()).getId();
        if (pessoaFisicaId != null || pessoaFisicaId == pessoa.getPessoaFisica().getId()) {
            throw new PessoaFisicaJaCadastrada();
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
        } catch (Exception e) {
            throw new EstadoCivilNaoEncontrado();
        }

        pessoa.getPessoaFisica().setPessoa(pessoa);
        return pessoa;
    }

    public Pessoa validarPessoaJuridica(Pessoa pessoa) {
        if (pessoa.getPessoaJuridica() == null) {
            throw new PessoaJuridicaNaoEncontrada();
        }

        if (pessoa.getPessoaJuridica().getId() == null) {
            if (this.findOneByCNPJ(pessoa.getPessoaJuridica().getCnpj()) != null) {
                throw new PessoaJuridicaJaCadastrada();
            }
        }

        if (Utils.validarCNPJ(pessoa.getPessoaJuridica().getCnpj()) == false) {
            throw new PessoaJuridicaCNPJInvalido();
        }
        pessoa.getPessoaJuridica().setPessoa(pessoa);

        return pessoa;

    }

}
