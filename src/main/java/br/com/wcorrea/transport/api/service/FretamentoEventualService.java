package br.com.wcorrea.transport.api.service;

import br.com.wcorrea.transport.api.model.fretamento.*;
import br.com.wcorrea.transport.api.model.pessoa.Cidade;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.model.pessoa.PessoaTipo;
import br.com.wcorrea.transport.api.model.veiculo.Veiculo;
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
import java.math.BigDecimal;
import java.util.*;

@Service
public class FretamentoEventualService {

    @Autowired
    private FretamentoEventualEventualRepository fretamentoEventualRepository;

    @Autowired
    private PessoaService pessoaService;

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private VeiculoService veiculoService;

    @Autowired
    ServletContext context;

    public Page<FretamentoEventual> listarTodos(FretamentoEventualFiltro filtro, Pageable paginacao) {

        if(StringUtils.isNotBlank(filtro.getVeiculoKey())){
            filtro.setVeiculoId(veiculoService.buscarPorId(filtro.getVeiculoKey()).getId());
        }
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

        f.setSituacao(f.getCliente() != null ? FretamentoEventalTipo.NAO_CONTRATADO_CLIENTE : FretamentoEventalTipo.NAO_CONTRATADO_CONTATO);
        f.setSituacaoData(new Date());
        return fretamentoEventualRepository.saveAndFlush(f);
    }

    @Transactional
    public FretamentoEventual ativarContrato(Long id) {
        FretamentoEventual f = findOne(id);
        f.setSituacaoData(new Date());
        f.setSituacao(f.getCliente() != null ? FretamentoEventalTipo.ORCAMENTO_CLIENTE : FretamentoEventalTipo.ORCAMENTO_CONTATO);
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
        } else {
            fretamentoEventual.setCliente(null);
        }

        //VERIFICA A DATA INICIAL DO ESTATUDO DO ORÇAMENTO
        if (fretamentoEventual.getSituacaoData() == null) {
            fretamentoEventual.setSituacaoData(new Date());
        }

        //VERIFICA SE O PERIODO ENTRE AS DATA É VALIDO
        if (fretamentoEventual.getItinerario().getPartida().after(fretamentoEventual.getItinerario().getRetorno())) {
            throw new RegraDeNegocio("Data de partida n\u00e3o pode ser maior que a data de retorno");
        }

        // VERIFICA SE A CIDADE DE PARTIDA EXISTE
        try {
            Cidade c1 = cidadeService.buscarPorId(fretamentoEventual.getItinerario().getPartidaCidade().getId());
            if (c1 == null) {
                throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
            }
            fretamentoEventual.getItinerario().setPartidaCidade(c1);
        } catch (Exception e) {
            throw new RegraDeNegocio("Cidade de partida n\u00e3o encontrada.");
        }

        //VERIFICA SE A CIDADE DE RETORNO EXISTE
        try {
            Cidade c2 = cidadeService.buscarPorId(fretamentoEventual.getItinerario().getRetornoCidade().getId());
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
                throw new RegraDeNegocio("Motorista 1 n\u00e3o encontrado.");
            }
            fretamentoEventual.getCusto().setMotorista1(m1);
        } catch (Exception e) {
            throw new RegraDeNegocio("Motorista 1 n\u00e3o encontrado.");
        }

        // VERIFICA SE O MOTORISTA 2 SELECIONADO EXISTE
        if (fretamentoEventual.getCusto().getMotorista2() != null) {
            try {
                Pessoa m2 = pessoaService.buscarPorId(fretamentoEventual.getCusto().getMotorista2().getId());
                if (m2 == null) {
                    throw new RegraDeNegocio("Motorista 2 n\u00e3o encontrado.");
                }
                fretamentoEventual.getCusto().setMotorista2(m2);
            } catch (Exception e) {
                throw new RegraDeNegocio("Motorista 2 n\u00e3o encontrado.");
            }
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

    public byte[] contratoTermoResponsabilidadeMotorista(String key) throws Exception {
        FretamentoEventual f = findOne(buscarPorKey(key));

        if (f.getCliente() == null) {
            throw new RegraDeNegocio("Fretamento não contém um cadastro de cliente completo, verifique e tente novamente!");
        }

        List<FretamentoEventualRelatorio.TermoResponsabilidadeMotorista> dados = new ArrayList<>();
        dados.add(new FretamentoEventualRelatorio.TermoResponsabilidadeMotorista(f.getCusto().getMotorista1()));
        if (f.getCusto().getMotorista2() != null) {
            dados.add(new FretamentoEventualRelatorio.TermoResponsabilidadeMotorista(f.getCusto().getMotorista2()));
        }

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        parametros.put("VEICULO", f.getItinerario().getVeiculo().getPlaca().toUpperCase());
        parametros.put("DATA_PARTIDA", Utils.getDataPorExtenso(f.getItinerario().getPartida()));
        parametros.put("DATA_RETORNO", Utils.getDataPorExtenso(f.getItinerario().getPrevisaoChegadaRetorno()));
        parametros.put("DATA_REALIZACAO_VIAGEM", f.getEmpresa().getCidade().getNome() + ", " + Utils.getDataPorExtenso(f.getItinerario().getPartida()));

        parametros.put("EMPRESA_RAZAO", f.getEmpresa().getNome().toUpperCase());
        parametros.put("EMPRESA_CNPJ_IE", "CNPJ:" + f.getEmpresa().getPessoaJuridica().getCnpj() + "            " + "IE:" + f.getEmpresa().getPessoaJuridica().getInscricaoEstadual());
        parametros.put("EMPRESA_ENDERECO", f.getEmpresa().getEndereco() + ", " + f.getEmpresa().getBairro() + " - " + f.getEmpresa().getCidade().getNome() + "/" + f.getEmpresa().getCidade().getEstado().getIniciais());
        parametros.put("EMPRESA_CEP_FONE", "CEP: " + f.getEmpresa().getCep() + "  -  FONE: " + f.getEmpresa().getTelefone1());
        parametros.put("EMPRESA_EMAIL", f.getEmpresa().getEmail());
        parametros.put("CONTRATO_CODIGO", "Contrato de Fretamento Eventual: " + f.getNumeroContrato());
        parametros.put("IMAGEM_LOGO", this.getClass().getResource("/relatorios/Logo.png").getPath());

        InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/FretamentoEventualContratoTermoResponsabilidadeMotorista.jasper");

        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dados));
        return JasperExportManager.exportReportToPdf(jasperPrint);
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

    public byte[] contratoRelatorioViagem(String key) throws Exception {
        FretamentoEventual f = findOne(buscarPorKey(key));

        if (f.getCliente() == null) {
            throw new RegraDeNegocio("Fretamento não contém um cadastro de cliente completo, verifique e tente novamente!");
        }

        //FORCA O RELATORIO TER 1 PAGINA
        List<FretamentoEventualRelatorio.TermoResponsabilidadeMotorista> dados = new ArrayList<>();
        dados.add(new FretamentoEventualRelatorio.TermoResponsabilidadeMotorista(f.getCusto().getMotorista1()));

        Map<String, Object> parametros = new HashMap<>();
        parametros.put("REPORT_LOCALE", new Locale("pt", "BR"));

        parametros.put("EMPRESA_RAZAO", f.getEmpresa().getNome().toUpperCase());
        parametros.put("EMPRESA_CNPJ_IE", "CNPJ:" + f.getEmpresa().getPessoaJuridica().getCnpj() + "            " + "IE:" + f.getEmpresa().getPessoaJuridica().getInscricaoEstadual());
        parametros.put("EMPRESA_ENDERECO", f.getEmpresa().getEndereco() + ", " + f.getEmpresa().getBairro() + " - " + f.getEmpresa().getCidade().getNome() + "/" + f.getEmpresa().getCidade().getEstado().getIniciais());
        parametros.put("EMPRESA_CEP_FONE", "CEP: " + f.getEmpresa().getCep() + "  -  FONE: " + f.getEmpresa().getTelefone1());
        parametros.put("EMPRESA_EMAIL", f.getEmpresa().getEmail());
        parametros.put("CONTRATO_CODIGO", "Contrato de Fretamento Eventual: " + f.getNumeroContrato());
        parametros.put("IMAGEM_LOGO", this.getClass().getResource("/relatorios/").getPath() + "Logo.png");

        parametros.put("VEICULO", "Frota: " + f.getItinerario().getVeiculo().getFrota() + " - Placa: " + f.getItinerario().getVeiculo().getPlaca().toUpperCase() + ", com capacidade de " + f.getItinerario().getVeiculo().getQtdLugares() + " lugares");
        parametros.put("DATA_PARTIDA", Utils.getDataPorExtenso(f.getItinerario().getPartida()));
        parametros.put("DATA_RETORNO", Utils.getDataPorExtenso(f.getItinerario().getRetorno()));
        parametros.put("DATA_REALIZACAO_VIAGEM", f.getEmpresa().getCidade().getNome() + ", " + Utils.getDataPorExtenso(f.getItinerario().getPartida()));
        parametros.put("CLIENTE", f.getCliente().getNome().toUpperCase());
        parametros.put("CLIENTE_TELEFONE1", f.getCliente().getTelefone1Formatado());
        parametros.put("CLIENTE_TELEFONE2", StringUtils.isNotBlank(f.getCliente().getTelefone2()) ? " - " + f.getCliente().getTelefone2Formatado() : "");

        parametros.put("ORIGEM", f.getItinerario().getPartidaCidade().getNome() + "/" + f.getItinerario().getPartidaCidade().getEstado().getIniciais());
        parametros.put("ORIGEM_SAIDA_DATA_HORA", Utils.getDataPorExtenso(f.getItinerario().getPartida()) + " as " + Utils.getHoraFormatada(f.getItinerario().getPartida()) + ".");
        parametros.put("ORIGEM_LOCAL_SAIDA", StringUtils.isNotBlank(f.getItinerario().getLocalSaida()) ? f.getItinerario().getLocalSaida() : "");
        parametros.put("DESTINO", f.getItinerario().getRetornoCidade().getNome() + "/" + f.getItinerario().getRetornoCidade().getEstado().getIniciais());
        parametros.put("DESTINO_SAIDA_DATA_HORA", Utils.getDataPorExtenso(f.getItinerario().getRetorno()) + " as " + Utils.getHoraFormatada(f.getItinerario().getRetorno()) + ".");
        parametros.put("DESTINO_LOCAL_SAIDA", StringUtils.isNotBlank(f.getItinerario().getLocalRetorno()) ? f.getItinerario().getLocalRetorno() : "");
        parametros.put("ITINERARIO_OBS", StringUtils.isNotBlank(f.getItinerario().getObsItineratio()) ? f.getItinerario().getObsItineratio() : "");

        parametros.put("MOTORISTA1", f.getCusto().getMotorista1().getNome() + "   -   Total de Diária(s): " + Utils.formatarDinheiroRS(f.getCusto().getValorMotorista1Diaria()));
        parametros.put("MOTORISTA2", f.getCusto().getMotorista2() != null ? (f.getCusto().getMotorista2().getNome() + "" +
                "   -   Total de Diária(s): " + ((f.getCusto().getValorMotorista2Diaria() != null && f.getCusto().getValorMotorista2Diaria().compareTo(BigDecimal.ZERO) == 1) ? Utils.formatarDinheiroRS(f.getCusto().getValorMotorista2Diaria()) : "")) : "");

        parametros.put("ABASTECIMENTO", relatorioViagemCalcularAbastecimentoVeiculo(f.getItinerario().getKmPercorridoQuantidade(), f.getItinerario().getVeiculo().getCapacidadeTanqueCombustivelLts(), f.getCusto().getCombustivelValor(), f.getItinerario().getVeiculo().getConsumoAtual()));
        parametros.put("PEDAGIO", relatorioViagemVerificarCobrancaAutomatica(f.getCusto().getValorPedagio(), f.getCusto().isCobrancaAutomatica()));
        parametros.put("ESTACIONAMENTO", (f.getCusto().getValorEstacionamento() != null && f.getCusto().getValorEstacionamento().compareTo(BigDecimal.ZERO) == 1) ? Utils.formatarDinheiroRS(f.getCusto().getValorEstacionamento()) : "");
        parametros.put("GELO", (f.getCusto().getValorGelo() != null && f.getCusto().getValorGelo().compareTo(BigDecimal.ZERO) == 1) ? Utils.formatarDinheiroRS(f.getCusto().getValorGelo()) : "");
        parametros.put("GASTOS_EXTRAS", (f.getCusto().getValorDespesasAdicionais() != null && f.getCusto().getValorDespesasAdicionais().compareTo(BigDecimal.ZERO) == 1) ? Utils.formatarDinheiroRS(f.getCusto().getValorDespesasAdicionais()) : "");
        parametros.put("HOSPEDAGEM", (f.getCusto().getValorHospedagem() != null && f.getCusto().getValorHospedagem().compareTo(BigDecimal.ZERO) == 1) ? Utils.formatarDinheiroRS(f.getCusto().getValorHospedagem()) : "");
        parametros.put("RESERVA", (f.getCusto().getValorDinheiroReserva() != null && f.getCusto().getValorDinheiroReserva().compareTo(BigDecimal.ZERO) == 1) ? Utils.formatarDinheiroRS(f.getCusto().getValorDinheiroReserva()) : "");

        parametros.put("VALOR_TOTAL_DESPESAS", relatorioViagemCalcularDespesas(f.getCusto(), f.getItinerario()));
        parametros.put("VALOR_A_SER_DEVOLVIDO", (f.getCusto().getValorDinheiroReserva() != null && f.getCusto().getValorDinheiroReserva().compareTo(BigDecimal.ZERO) == 1) ? "<style isBold='true' backcolor='yellow'>Caso NÃO tenha gastos extras o valor a ser devolvido será de: " + "<style forecolor='blue'>" + Utils.formatarDinheiroRS(f.getCusto().getValorDinheiroReserva()) + "</style></style>" : "");
        parametros.put("DATA_LIBERACAO_VIAGEM", Utils.getDataFormatada(new Date()));
        parametros.put("IMAGEM_VELOCIDADE_MAXIMA", this.getClass().getResource("/relatorios/").getPath() + "VelocidadeMaximaPermitida.jpg");


        InputStream inputStream = this.getClass().getResourceAsStream("/relatorios/FretamentoEventualContratoRelatorioViagem.jasper");

        JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, parametros, new JRBeanCollectionDataSource(dados));
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

    private String relatorioViagemCalcularAbastecimentoVeiculo(int totalKm, int capacidadeTanqueVeiculo, BigDecimal valorKm, BigDecimal mediaAtualVeiculo) {

        // TODO: COLOCAR ESSE 0.2 = 20% DO TANQUE DE COMBUSTIVEL NA TABELA DE PARAMETRO
        Double reservaDoTanque = capacidadeTanqueVeiculo * 0.2;

        Double litrosTotalParaViagem = totalKm / mediaAtualVeiculo.doubleValue();
        Double litrosNaoUsados = litrosTotalParaViagem - (capacidadeTanqueVeiculo - reservaDoTanque);

        if (litrosNaoUsados > 0) {
            return Utils.formatarDinheiroRS(valorKm.multiply(new BigDecimal(litrosNaoUsados))) + ", previsto gastar " + String.format("%.2f", litrosNaoUsados) + " lts, com valor estimado a " + Utils.formatarDinheiroRS(valorKm) + " ( Sem contar " + String.format("%.2f", reservaDoTanque) + " lts reserva no Tanque )";
        }
        return "";
    }

    private String relatorioViagemCalcularDespesas(FretamentoEventualCusto custo, FretamentoEventualItinerario itinerario) {
        BigDecimal total = BigDecimal.ZERO;

        if (!custo.isCobrancaAutomatica() && custo.getValorPedagio() != null) {
            total = total.add(custo.getValorPedagio()); //VERIFICAR SEM PARAR
        }
        total = total.add(custo.getValorEstacionamento() != null ? custo.getValorEstacionamento() : BigDecimal.ZERO)
//                .add(custo.getValorAgua())
                .add(custo.getValorGelo() != null ? custo.getValorGelo() : BigDecimal.ZERO)
                .add(custo.getValorHospedagem() != null ? custo.getValorHospedagem() : BigDecimal.ZERO)
                .add(custo.getValorDespesasAdicionais() != null ? custo.getValorDespesasAdicionais() : BigDecimal.ZERO)

                .add(custo.getValorMotorista1Diaria())
                .add(custo.getValorMotorista2Diaria() != null ? custo.getValorMotorista2Diaria() : BigDecimal.ZERO);


        // TODO: COLOCAR ESSE 0.2 = 20% DO TANQUE DE COMBUSTIVEL NA TABELA DE PARAMETRO
        Double reservaDoTanque = itinerario.getVeiculo().getCapacidadeTanqueCombustivelLts() * 0.2;

        Double litrosTotalParaViagem = itinerario.getKmPercorridoQuantidade() / itinerario.getVeiculo().getConsumoReal().doubleValue();
        Double litrosNaoUsados = litrosTotalParaViagem - (itinerario.getVeiculo().getCapacidadeTanqueCombustivelLts() - reservaDoTanque);
        if (litrosNaoUsados > 0) {
            total = total.add(custo.getCombustivelValor().multiply(new BigDecimal(litrosNaoUsados)));
        }

        return "Valor total disponibilizado para viagem: " + Utils.formatarDinheiroRS(total);
    }

    private String relatorioViagemVerificarCobrancaAutomatica(BigDecimal pedagio, boolean cobrancaAutomatica) {
        if (pedagio != null && pedagio.compareTo(BigDecimal.ZERO) == 1) {
            if (!cobrancaAutomatica) {
                return Utils.formatarDinheiroRS(pedagio);
            }
            return "SEM PARAR";
        }

        return "";
    }
}
