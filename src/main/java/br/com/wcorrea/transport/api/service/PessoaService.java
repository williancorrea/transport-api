package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.model.pessoa.*;
import br.com.wcorrea.transport.api.repository.CidadeRepository;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaRepository;
import br.com.wcorrea.transport.api.service.exception.*;
import br.com.wcorrea.transport.api.utils.Criptografia;
import br.com.wcorrea.transport.api.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * ClasseDespesa responsavel por manipular as regras de negocio de pessoa
 */
@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private EstadoCivilRepository estadoCivilRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    public Pessoa save(Pessoa pessoaNovo) {
        pessoaNovo.setId(null);
        pessoaNovo = validarPessoa(pessoaNovo);

        if (pessoaNovo.getTipo().equals(PessoaTipo.FISICA)) {
            pessoaNovo.setPessoaJuridica(null);
            pessoaNovo = this.validarPessoaFisica(pessoaNovo);
        } else {
            pessoaNovo.setPessoaFisica(null);
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

        pessoa = validarPessoa(pessoa);

//        BeanUtils.copyProperties(pessoa, objFound, "id", "pessoaFisica", "propriedades");
//        BeanUtils.copyProperties(pessoa.getPessoaFisica(), objFound.getPessoaFisica(),"id");

//        pessoa.setPropriedades(objFound.getPropriedades());
//        pessoa.getPropriedades().setDataAlteracao(LocalDateTime.now());
        return pessoaRepository.save(pessoa);
    }

    public Pessoa buscarPorId(Long id) {
        Optional<Pessoa> pessoaEncontrada = pessoaRepository.findById(id);
        if (!pessoaEncontrada.isPresent()) {
            throw new PessoaNaoEncontrada();
        }
        return pessoaEncontrada.get();
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

    public List<Pessoa> pesquisaClienteFornecedorCmb(PessoaFiltro filtro, Pageable pageable) {
        return pessoaRepository.findAll(filtro, pageable).getContent();
    }

    public Pessoa findOneByCNPJ(String cnpj) {
        return pessoaRepository.findOneByCNPJ(cnpj);
    }

    public Pessoa validarPessoa(Pessoa pessoa) {
        Pessoa pessoaEncontrada = pessoa.getId() != null ? this.buscarPorId(pessoa.getId()) : null;



        if (pessoa.getTipo().equals(PessoaTipo.FISICA)) {
            if (pessoa.getId() == null) {
                pessoa.getPessoaFisica().setId(null);
            }
            pessoa.setPessoaJuridica(null);
            pessoa = this.validarPessoaFisica(pessoa);
        } else {
            if (pessoa.getId() == null) {
                pessoa.getPessoaJuridica().setId(null);
            }
            pessoa.setPessoaFisica(null);
            pessoa = this.validarPessoaJuridica(pessoa);
        }

        if (pessoaEncontrada != null) {
            // IMPEDE A ALTERACAO DO CPF
            if (pessoa.getPessoaFisica() != null && !pessoa.getPessoaFisica().getCpf().equals(pessoaEncontrada.getPessoaFisica().getCpf())) {
                throw new PessoaFisicaNaoPodeAlterarValorCPF();
            }

            // IMPEDE A ALTERACAO DO CNPJ - PARA CADASTROS JA REALIZADOS
            if (pessoa.getPessoaJuridica() != null && !pessoa.getPessoaJuridica().getCnpj().equals(pessoaEncontrada.getPessoaJuridica().getCnpj())) {
                throw new PessoaJuridicaNaoPodeAlterarValorCNPJ();
            }
        }

        // VERIFICA SE A CIDADE SELECIONADA EXISTE NA BASE DE DADOS
        if (pessoa.getCidade() != null) {
            try {
                Optional<Cidade> cidadeEncontrada = cidadeRepository.findById(pessoa.getCidade().getId());
                if (!cidadeEncontrada.isPresent()) {
                    throw new CidadeNaoEncontrada();
                }
                pessoa.setCidade(cidadeEncontrada.get());
            } catch (Exception e) {
                throw new CidadeNaoEncontrada();
            }
        }


        if (pessoa.getId() == null) {
//            Pessoa finalPessoa = pessoa;
//            pessoa.getListaPessoaTelefone().forEach(c -> c.setPessoa(finalPessoa));
//            pessoa.getListaPessoaContato().forEach(c -> c.setPessoa(finalPessoa));
//            pessoa.getListaPessoaEndereco().forEach(c -> c.setPessoa(finalPessoa));
//            pessoa.getListaPessoaAuditoria().forEach(c -> c.setPessoa(finalPessoa));
        } else {
            /*
                ADICIONANDO A LISTA DE TELEFONES
             */
//            pessoaEncontrada.getListaPessoaTelefone().clear();
//            pessoaEncontrada.getListaPessoaTelefone().addAll(pessoa.getListaPessoaTelefone());
//            pessoaEncontrada.getListaPessoaTelefone().forEach(c -> c.setPessoa(pessoaEncontrada));
            /*
                ADICIONANDO A LISTA DE CONTATOS
             */
//            pessoaEncontrada.getListaPessoaContato().clear();
//            pessoaEncontrada.getListaPessoaContato().addAll(pessoa.getListaPessoaContato());
//            pessoaEncontrada.getListaPessoaContato().forEach(c -> c.setPessoa(pessoaEncontrada));
            /*
                ADICIONANDO A LISTA DE ENDEREÇOS
             */
//            pessoaEncontrada.getListaPessoaEndereco().clear();
//            pessoaEncontrada.getListaPessoaEndereco().addAll(pessoa.getListaPessoaEndereco());
//            pessoaEncontrada.getListaPessoaEndereco().forEach(c -> c.setPessoa(pessoaEncontrada));
            /*
                ADICIONANDO A LISTA DE AUDITORIA
             */
//            pessoaEncontrada.getListaPessoaAuditoria().clear();
//            pessoaEncontrada.getListaPessoaAuditoria().addAll(pessoa.getListaPessoaAuditoria());
//            pessoaEncontrada.getListaPessoaAuditoria().forEach(c -> c.setPessoa(pessoaEncontrada));

            BeanUtils.copyProperties(pessoa, pessoaEncontrada, "id", "listaPessoaTelefone", "listaPessoaContato", "listaPessoaEndereco", "listaPessoaAuditoria");
        }
        /**
         * CONSTROI O OBJETO DE AUDITORIA
         */
//        pessoa.getListaPessoaAuditoria().add(construirObjAuditoria(pessoa, pessoaEncontrada));
        return pessoa;
    }

    private Pessoa validarPessoaFisica(Pessoa pessoa) {
        // GARANTE QUE OS DADOS DE PESSOA FISICA EXITEM
        if (pessoa.getPessoaFisica() == null) {
            throw new PessoaFisicaNaoEncontrada();
        }
        // GARANTE UM CPF VALIDO
        if (Utils.validarCPF(pessoa.getPessoaFisica().getCpf()) == false) {
            throw new PessoaFisicaCPFInvalido();
        }
        // GARANTE UM CPF UNICO
        if (this.pessoaRepository.verificarCPFJaCadastrado(pessoa.getPessoaFisica().getCpf(), pessoa.getId())) {
            throw new PessoaFisicaJaCadastrada();
        }
        // VERIFICA SE O ESTADO CIVIL EXISTE NA BASE DE DADOS
        if (pessoa.getPessoaFisica().getEstadoCivil() != null) {
            try {
                Optional<EstadoCivil> estadoCivilEncontrado = estadoCivilRepository.findById(pessoa.getPessoaFisica().getEstadoCivil().getId());
                if (!estadoCivilEncontrado.isPresent()) {
                    throw new EstadoCivilNaoEncontrado();
                }
                pessoa.getPessoaFisica().setEstadoCivil(estadoCivilEncontrado.get());
            } catch (Exception e) {
                throw new EstadoCivilNaoEncontrado();
            }
        }

        pessoa.getPessoaFisica().setPessoa(pessoa);
        return pessoa;
    }

    private Pessoa validarPessoaJuridica(Pessoa pessoa) {
        // GARANTE QUE OS DADOS DE PESSOA JURIDICA EXISTE
        if (pessoa.getPessoaJuridica() == null) {
            throw new PessoaJuridicaNaoEncontrada();
        }
        // GARANTE UM CNPJ VALIDO
        if (Utils.validarCNPJ(pessoa.getPessoaJuridica().getCnpj()) == false) {
            throw new PessoaJuridicaCNPJInvalido();
        }
        // GARANTE UM CNPJ UNICO
        if (this.pessoaRepository.verificarCNPJJaCadastrado(pessoa.getPessoaJuridica().getCnpj(), pessoa.getId())) {
            throw new PessoaJuridicaJaCadastrada();
        }

        pessoa.getPessoaJuridica().setPessoa(pessoa);
        return pessoa;
    }

    private PessoaAuditoria construirObjAuditoria(Pessoa novo, Pessoa antigo) {
        PessoaAuditoria aud = new PessoaAuditoria();
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
        aud.setControle(new PropriedadesComuns());
        return aud;
    }
}
