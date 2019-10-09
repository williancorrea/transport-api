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

    /**
     * Prepara os dados de motorista para atualização
     *
     * @param id
     * @param pessoa
     * @return
     */
    public Pessoa updateMotorista(Long id, Pessoa pessoa) {
        Pessoa objFound = buscarPorId(id);

//        objFound.getPessoaFisica().setCpf(pessoa.getPessoaFisica().getCpf());
        objFound.setNome(pessoa.getNome());
        objFound.setFantasia(pessoa.getFantasia());
        objFound.getPessoaFisica().setRg(pessoa.getPessoaFisica().getRg());
        objFound.setEmail(pessoa.getEmail());
        objFound.setTelefone1(pessoa.getTelefone1());
        objFound.setTelefone1Obs(pessoa.getTelefone1Obs());
        objFound.setTelefone2(pessoa.getTelefone2());
        objFound.setTelefone2Obs(pessoa.getTelefone2Obs());
        objFound.setCidade(pessoa.getCidade());
        objFound.setBairro(pessoa.getBairro());
        objFound.setCep(pessoa.getCep());
        objFound.setEndereco(pessoa.getEndereco());
        objFound.setObs(pessoa.getObs());
        objFound.getPessoaFisica().setCnhNumero(pessoa.getPessoaFisica().getCnhNumero());
        objFound.getPessoaFisica().setOrgaoRg(pessoa.getPessoaFisica().getOrgaoRg());
        objFound.getPessoaFisica().setCnhPrimeiraHabilitacao(pessoa.getPessoaFisica().getCnhPrimeiraHabilitacao());
        objFound.getPessoaFisica().setCnhEmissaoData(pessoa.getPessoaFisica().getCnhEmissaoData());
        objFound.getPessoaFisica().setCnhEmissaoCidade(pessoa.getPessoaFisica().getCnhEmissaoCidade());
        objFound.getPessoaFisica().setCnhVencimento(pessoa.getPessoaFisica().getCnhVencimento());
        objFound.getPessoaFisica().setDataNascimento(pessoa.getPessoaFisica().getDataNascimento());
        objFound.getPessoaFisica().setNomeMae(pessoa.getPessoaFisica().getNomeMae());
        objFound.getPessoaFisica().setNomePai(pessoa.getPessoaFisica().getNomePai());
        objFound.getPessoaFisica().setSexo(pessoa.getPessoaFisica().getSexo());
        objFound.getPessoaFisica().setCnhCategoria(pessoa.getPessoaFisica().getCnhCategoria());

        return update(objFound.getId(), objFound);
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

    public Long descriptografarKey(String key) {
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

    public List<Pessoa> pesquisaEmpresaRosinhaCmb(PessoaFiltro filtro, Pageable pageable) {
        filtro.setEmpresaRosinhaTransportes(true);
        filtro.setSomenteAtivo(true);
        return pessoaRepository.findAll(filtro, pageable).getContent();
    }

    public List<Pessoa> pesquisaRepresentanteComercialEmpresaRosinhaCmb(PessoaFiltro filtro, Pageable pageable) {
        filtro.setRepresentanteComercialRosinhaTransportes(true);
        filtro.setSomenteAtivo(true);
        return pessoaRepository.findAll(filtro, pageable).getContent();
    }

    public Pessoa motoristaAtivar(String key) {
        Pessoa p = buscarPorId(descriptografarKey(key));
        if (p.getTipo().equals(PessoaTipo.JURIDICA)) {
            throw new RegraDeNegocio("Pessoa jur\u00eddica não pode ser motorista");
        }
        p.getPessoaFisica().setInativoMotorista(false);
        return update(p.getId(), p);
    }

    public Pessoa motoristaDesativar(String key) {
        Pessoa p = buscarPorId(descriptografarKey(key));
        if (p.getTipo().equals(PessoaTipo.JURIDICA)) {
            throw new RegraDeNegocio("Pessoa jur\u00eddica não pode ser motorista");
        }
        p.getPessoaFisica().setInativoMotorista(true);
        return update(p.getId(), p);
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
