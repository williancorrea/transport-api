package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.model.pessoa.EstadoCivil;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.model.pessoa.PessoaTipo;
import br.com.wcorrea.transport.api.repository.estadoCidade.CidadeRepository;
import br.com.wcorrea.transport.api.repository.estadoCivil.EstadoCivilRepository;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaFiltro;
import br.com.wcorrea.transport.api.repository.pessoa.PessoaRepository;
import br.com.wcorrea.transport.api.service.exception.*;
import br.com.wcorrea.transport.api.utils.Criptografia;
import br.com.wcorrea.transport.api.utils.Utils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        if (id != null && id > 0) {
            Optional<Pessoa> obj = pessoaRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new PessoaNaoEncontrada();
    }

    public Pessoa buscarPorId(Pessoa pessoa) {
        if (pessoa == null) {
            throw new PessoaNaoEncontrada();
        }
        return this.buscarPorId(pessoa.getId());
    }

    public Pessoa buscarPorCPF(String cpf) {
        if (StringUtils.isBlank(cpf)) {
            throw new PessoaNaoEncontrada();
        }
        return pessoaRepository.findOneByCPF(cpf);
    }

    public Pessoa buscarPorCNPJ(String cnpj) {
        if (StringUtils.isBlank(cnpj)) {
            throw new PessoaNaoEncontrada();
        }
        return pessoaRepository.findOneByCNPJ(cnpj);
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new PessoaNaoEncontrada();
        }
    }

    public List<Pessoa> pesquisaClienteFornecedorCmb(PessoaFiltro filtro, Pageable pageable) {
        filtro.setSomenteAtivo(true);
        return pessoaRepository.findAll(filtro, pageable).getContent();
    }

    public List<Pessoa> pesquisaMotoristaCmb(PessoaFiltro filtro, Pageable pageable) {
        filtro.setMotorista(true);
        filtro.setSomenteAtivo(true);
        return pessoaRepository.findAll(filtro, pageable).getContent();
    }

    public Pessoa validarPessoa(Pessoa pessoa) {
        Pessoa pessoaEncontrada = pessoa.getId() != null ? this.buscarPorId(pessoa.getId()) : null;

        if (pessoa.isPessoaFisica()) {
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
            // IMPEDE A ALTERACAO DO CPF - PARA CADASTROS JA REALIZADOS
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
        } else {
            throw new CidadeNaoEncontrada();
        }

        // FAZ A COPIA DAS PROPRIEDADES DO OBJETO
        if (pessoa.getId() != null) {
            BeanUtils.copyProperties(pessoa, pessoaEncontrada, "id", "controle");
            pessoa = pessoaEncontrada;
        }
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

}
