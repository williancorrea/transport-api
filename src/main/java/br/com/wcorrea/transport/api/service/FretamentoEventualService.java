package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.Veiculo;
import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventalTipo;
import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventual;
import br.com.wcorrea.transport.api.model.fretamento.FretamentoEventualRelatorio;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.model.pessoa.PessoaTipo;
import br.com.wcorrea.transport.api.repository.fretamentoEventual.FretamentoEventualEventualRepository;
import br.com.wcorrea.transport.api.repository.fretamentoEventual.FretamentoEventualFiltro;
import br.com.wcorrea.transport.api.service.exception.FretamentoEventualNaoEncontrado;
import br.com.wcorrea.transport.api.service.exception.RegraDeNegocio;
import br.com.wcorrea.transport.api.service.exception.veiculo.VeiculoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import br.com.wcorrea.transport.api.utils.Utils;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.*;

@Service
public class FretamentoEventualService {

    @Autowired
    private FretamentoEventualEventualRepository fretamentoEventualRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private EstadoCidadeService estadoCidadeService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    ServletContext context;

    public Page<FretamentoEventual> listarTodos(FretamentoEventualFiltro filtro, Pageable paginacao) {
        return fretamentoEventualRepository.findAll(filtro, paginacao);
    }

    public FretamentoEventual findOne(Long id) {
        if (id != null && id > 0) {
            Optional<FretamentoEventual> obj = fretamentoEventualRepository.findById(id);
            if (obj.isPresent()) {
                return obj.get();
            }
        }
        throw new FretamentoEventualNaoEncontrado();
    }

    public Long buscarPorKey(String key) {
        try {
            return new Criptografia().getKey(key);
        } catch (Exception e) {
            throw new FretamentoEventualNaoEncontrado();
        }
    }

    @Transactional
    public FretamentoEventual salvar(FretamentoEventual fretamentoEventual) {
        fretamentoEventual = this.prepararFretamentoParaPersistencia(fretamentoEventual);
        return fretamentoEventualRepository.save(fretamentoEventual);
    }

    @Transactional
    public FretamentoEventual atualizar(Long id, FretamentoEventual fretamentoEventual) {
        FretamentoEventual fretamentoEncontrado = findOne(id);

        fretamentoEventual.setId(fretamentoEncontrado.getId());
        fretamentoEventual.setNumeroContrato(Utils.StrZeroEsquerda(fretamentoEncontrado.getId().toString(), 5));
        fretamentoEventual.setSituacaoData(fretamentoEncontrado.getSituacaoData());

        fretamentoEventual = this.prepararFretamentoParaPersistencia(fretamentoEventual);
        return fretamentoEventualRepository.saveAndFlush(fretamentoEventual);
    }

    @Transactional
    public FretamentoEventual cancelarContrato(Long id) {
        FretamentoEventual f = findOne(id);
        f.setSituacao(FretamentoEventalTipo.NAO_CONTRATADO);
        f.setSituacaoData(new Date());
        return fretamentoEventualRepository.saveAndFlush(f);
    }

    @Transactional
    public FretamentoEventual ativarContrato(Long id) {
        FretamentoEventual f = findOne(id);
        f.setSituacaoData(new Date());
        f.setSituacao(FretamentoEventalTipo.AGENDADO);
        if(f.getCliente() == null){
            f.setSituacao(FretamentoEventalTipo.ORCAMENTO);
        }

        return fretamentoEventualRepository.saveAndFlush(f);
    }

    @Transactional
    public FretamentoEventual contratarFretamento(Long id) {
        FretamentoEventual f = findOne(id);
        f.setSituacaoData(new Date());
        f.setSituacao(FretamentoEventalTipo.CONTRATADO);
        return fretamentoEventualRepository.saveAndFlush(f);
    }



    private FretamentoEventual prepararFretamentoParaPersistencia(FretamentoEventual fretamentoEventual) {

        if (fretamentoEventual.getCliente() != null && ((fretamentoEventual.getCliente().getPessoaFisica() != null && StringUtils.isNotBlank(fretamentoEventual.getCliente().getPessoaFisica().getCpf())) || (fretamentoEventual.getCliente().getPessoaJuridica() != null && StringUtils.isNotBlank(fretamentoEventual.getCliente().getPessoaJuridica().getCnpj())))) {
            /**
             * FAZ A ATUALIZACAO DO CLIENTE SOMENTE NOS CAMPOS ALTERADOS NO FRETAMENTO
             */
            if (fretamentoEventual.getCliente() != null && fretamentoEventual.getCliente().isEditando()) {
                Pessoa pEncontrada = pessoaService.buscarPorId(fretamentoEventual.getCliente().getId());

                if (pEncontrada.getTipo() == PessoaTipo.FISICA) {
                    pEncontrada.getPessoaFisica().setRg(fretamentoEventual.getCliente().getPessoaFisica().getRg());
                }

                if (pEncontrada.getTipo() == PessoaTipo.JURIDICA) {
                    pEncontrada.getPessoaJuridica().setInscricaoEstadual(fretamentoEventual.getCliente().getPessoaJuridica().getInscricaoEstadual());
                }

                pEncontrada.setImagem(fretamentoEventual.getCliente().getImagem());
                pEncontrada.setFantasia(fretamentoEventual.getCliente().getFantasia());
                pEncontrada.setNome(fretamentoEventual.getCliente().getNome());
                pEncontrada.setEmail(fretamentoEventual.getCliente().getEmail());
                pEncontrada.setTelefone1(fretamentoEventual.getCliente().getTelefone1());
                pEncontrada.setTelefone1Obs(fretamentoEventual.getCliente().getTelefone1Obs());
                pEncontrada.setTelefone2(fretamentoEventual.getCliente().getTelefone2());
                pEncontrada.setTelefone2Obs(fretamentoEventual.getCliente().getTelefone2Obs());
                pEncontrada.setCidade(fretamentoEventual.getCliente().getCidade());
                pEncontrada.setBairro(fretamentoEventual.getCliente().getBairro());
                pEncontrada.setCep(fretamentoEventual.getCliente().getCep());
                pEncontrada.setEndereco(fretamentoEventual.getCliente().getEndereco());
                pEncontrada.setObs(fretamentoEventual.getCliente().getObs());

                fretamentoEventual.setCliente(pEncontrada);
            }

            fretamentoEventual.setCliente(pessoaService.validarPessoa(fretamentoEventual.getCliente()));
        }else{
            fretamentoEventual.setCliente(null);
        }

        //VERIFICA A DATA INICIAL DO ESTATUDO DO ORÇAMENTO
        if(fretamentoEventual.getSituacaoData() == null){
            fretamentoEventual.setSituacaoData(new Date());
        }

        //VERIFICA SE O PERIODO ENTRE AS DATA É VALIDO
        if (fretamentoEventual.getItinerario().getPartida().after(fretamentoEventual.getItinerario().getRetorno())) {
            throw new RegraDeNegocio("Data de partida n\u00e3o pode ser maior que a data de retorno");
        }

        // VERIFICA SE A CIDADE DE PARTIDA EXISTE
        try {
            Cidade c1 = estadoCidadeService.buscarPorId(fretamentoEventual.getItinerario().getPartidaCidade().getId());
            if (c1 == null) {
                throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
            }
            fretamentoEventual.getItinerario().setPartidaCidade(c1);
        } catch (Exception e) {
            throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
        }

        //VERIFICA SE A CIDADE DE RETORNO EXISTE
        try {
            Cidade c2 = estadoCidadeService.buscarPorId(fretamentoEventual.getItinerario().getRetornoCidade().getId());
            if (c2 == null) {
                throw new RegraDeNegocio("Cidade de retorno n\u00e3o encontrada.");
            }
            fretamentoEventual.getItinerario().setRetornoCidade(c2);
        } catch (Exception e) {
            throw new RegraDeNegocio("Cidade de retorno n\u00e3o encontrada.");
        }

        // VERIFICA SE O MOTORISTA 1 SELECIONADO EXISTE
        try {
            Pessoa m1 = pessoaService.buscarPorId(fretamentoEventual.getCusto().getMotorista1().getId());
            if (m1 == null) {
                throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
            }
            fretamentoEventual.getCusto().setMotorista1(m1);
        } catch (Exception e) {
            throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
        }

        // VERIFICA SE O MOTORISTA 2 SELECIONADO EXISTE
        try {
            Pessoa m2 = pessoaService.buscarPorId(fretamentoEventual.getCusto().getMotorista2().getId());
            if (m2 == null) {
                throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
            }
            fretamentoEventual.getCusto().setMotorista2(m2);
        } catch (Exception e) {
            throw new RegraDeNegocio("Motorista n\u00e3o encontrado.");
        }

        // VERIFICA SE O VEICULO SELECIONADO EXISTE
        try {
            Veiculo v = veiculoService.buscarPorId(fretamentoEventual.getItinerario().getVeiculo().getId());
            if (v == null) {
                throw new VeiculoNaoEncontrado();
            }
            fretamentoEventual.getItinerario().setVeiculo(v);
        } catch (Exception e) {
            throw new VeiculoNaoEncontrado();
        }

        return fretamentoEventual;
    }

    public byte[] contratoPorFretamento(String key) throws Exception {
        FretamentoEventual f = findOne(buscarPorKey(key));

        if (f.getCliente() == null) {
            throw new RegraDeNegocio("Fretamento não contém um cadastro de cliente completo, verifique e tente novamente!");
        }

        List<FretamentoEventual> dados = new ArrayList<>();
        dados.add(f);

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));
        parametros.put("CONTRATO", new FretamentoEventualRelatorio(f).getContratoFretamentoEventual());
        parametros.put("EMPRESA_RAZAO", f.getEmpresa().getNome().toUpperCase());
//        parametros.put("EMPRESA_CNPJ_IE", "CNPJ:" + f.getEmpresa().getPessoaJuridica().getCnpjFormatado() + "            " + "IE:" + f.getEmpresa().getPessoaJuridica().getInscricaoEstadualFormatada());
        parametros.put("EMPRESA_CNPJ_IE", "CNPJ:" + f.getEmpresa().getPessoaJuridica().getCnpj() + "            " + "IE:" + f.getEmpresa().getPessoaJuridica().getInscricaoEstadual());
        parametros.put("EMPRESA_ENDERECO", f.getEmpresa().getEndereco() + ", " + f.getEmpresa().getBairro() + " - " + f.getEmpresa().getCidade().getNome() + "/" + f.getEmpresa().getCidade().getEstado().getIniciais());
        parametros.put("EMPRESA_CEP_FONE", "CEP: " + f.getEmpresa().getCep() + "  -  FONE: " + f.getEmpresa().getTelefone1());
        parametros.put("EMPRESA_EMAIL", f.getEmpresa().getEmail());
        parametros.put("CONTRATO_CODIGO", "Contrato de Fretamento Eventual: " + f.getNumeroContrato());
        parametros.put("IMAGEM_LOGO", this.getClass().getResource("/relatorios/Logo.png").getPath());

        InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/FretamentoEventualContrato.jasper");

        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dados));
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
