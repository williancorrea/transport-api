package br.com.wcorrea.transport.api.model.fretamento;

import br.com.wcorrea.transport.api.model.pessoa.PessoaTipo;
import br.com.wcorrea.transport.api.utils.NumeroExtenso;
import br.com.wcorrea.transport.api.utils.Utils;
import org.apache.commons.lang3.StringUtils;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class FretamentoEventualRelatorio {

    FretamentoEventual f;

    public FretamentoEventualRelatorio(FretamentoEventual fretamentoEventual) {
        this.f = fretamentoEventual;
    }

    public String getContratoFretamentoEventual() {

        StringBuilder contrato = new StringBuilder();


//        contrato.append(" teste \r Teste \n <b>Teste</b> \n Teste"
//                + "<style isBold='true' forecolor='green'>style</style>"
//                + "<style size='20' isBold='true' forecolor='black'>Vinte</style>"
//                + "<style size='15' isBold='true' forecolor='black'>Quinze</style>"
//                + "<style size='12' isBold='true' forecolor='black'>Doze</style>"
//                + "<style size='10' isBold='true' forecolor='black'>Dez</style>"
//                + "<style size='8' isBold='true' forecolor='black'>Oito</style>");

        contrato.append("\n")
                .append("<style size='12' isBold='true'>        CONTRATO PARTICULAR DE LOCAÇÃO DE VEÍCULO           </style>")
                .append("\n\n")

                .append("Pelo presente instrumento particular de contrato, de um lado <b>" + f.getEmpresa().getNome()+"</b>, com sede no Município de "
                    + f.getEmpresa().getCidade().getNome() +" / "+ f.getEmpresa().getCidade().getEstado().getIniciais() + " na Rua " + f.getEmpresa().getEndereco() + " - " + f.getEmpresa().getBairro()
                    + ", inscrita no CNPJ sob o n.º "+ f.getEmpresa().getPessoaJuridica().getCnpjFormatado() + " I.E. " + f.getEmpresa().getPessoaJuridica().getInscricaoEstadualFormatada()
                    + ", doravante simplesmente denominada CONTRATADA e de outro lado " + ((f.getCliente().getTipo().equals(PessoaTipo.FISICA)) ? "o <b>Sr.(a) " : "<b>") + f.getCliente().getNome()
                    + "</b>, com sede no Município de " + f.getCliente().getCidade().getNome() +" / "+ f.getCliente().getCidade().getEstado().getIniciais() + ", na Rua. "
                    + f.getCliente().getEndereco() + " - "+f.getCliente().getBairro() +", inscrita no " +
                        (
                            (f.getCliente().getTipo().equals(PessoaTipo.FISICA))
                                ? "CPF sob o nº " + f.getCliente().getPessoaFisica().getCpfFormatado() + (StringUtils.isBlank(f.getCliente().getPessoaFisica().getRg()) ? "" : " e RG " + f.getCliente().getPessoaFisica().getRg())
                                : "CNPJ sob o nº " + f.getCliente().getPessoaJuridica().getCnpjFormatado() + (StringUtils.isBlank(f.getCliente().getPessoaJuridica().getInscricaoEstadual()) ? "" : " e Insc. Estadual " + f.getCliente().getPessoaJuridica().getInscricaoEstadualFormatada())
                        )
                    +", doravante denominada simplesmente CONTRATANTE, têm entre si, como justo e pactuado, a contratação dos " +
                    "serviços de transporte de pessoas, mediante as cláusulas e condições seguintes:")
                .append("\n\n")

                .append("<b>CLÁUSULA PRIMEIRA - OBJETO DO CONTRATO <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></b>").append("\n")
                .append("1.0 - O Presente te como OBJETO, a locação do veículo FROTA " + f.getItinerario().getVeiculo().getFrota() + ", Placa - " + f.getItinerario().getVeiculo().getPlaca()
                        + ", com a capacidade de " + f.getItinerario().getVeiculo().getQtdLugares() + " lugares, viagem a ser realiza saindo de " + f.getItinerario().getPartidaCidade().getNome() + "/"+f.getItinerario().getPartidaCidade().getEstado().getIniciais()
                        + " no dia " + Utils.getDataPorExtenso(f.getItinerario().getPartida()) + " as " + new SimpleDateFormat("HH:mm").format(f.getItinerario().getPartida()) +", com destino a "
                        + f.getItinerario().getRetornoCidade().getNome() + "/" + f.getItinerario().getRetornoCidade().getEstado().getIniciais() + " com retorno no dia " + Utils.getDataPorExtenso(f.getItinerario().getRetorno())
                        + " as " +  new SimpleDateFormat("HH:mm").format(f.getItinerario().getRetorno())).append("\n")
                .append("1.1 - Valor Acordado em "+ NumberFormat.getCurrencyInstance(new Locale("pt", "BR")).format(f.getCusto().getViagemPrecoFinal()) +" (" + new NumeroExtenso(f.getCusto().getViagemPrecoFinal()).toMonetario() + ")")
                .append("\n\n")

                .append("<b>CLÁUSULA SEGUNDA - DO VEÍCULO E SUA UTILIZAÇÃO <font color='white'> _ _  _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></b>").append("\n")
                .append("2.1 - O veículo a ser locado encontra-se identificado neste contrato na cláusula 1, bem como suas condições gerais no parágrafo único.").append("\n")
                .append("2.2 - <b>A CONTRATADA</b> deverá apresentar lista com o nome completo e número de RG de todos passageiros.").append("\n\n")

                .append("<b>CLÁUSULA TERCEIRA - DO PAGAMENTO DA LOCAÇÃO E DO VALOR <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></b>").append("\n")
                .append("3.1 - O Valor do objeto deste contrato fica acordado conforme ja citada acima, valor fixo pago avista até a data da viagem.").append("\n")
                .append("3.2 - Depósito em conta bancária. <b>(Caixa Econômica Federal: Agencia 0901, Conta Corrente 1328-8, Operação 003)</b>").append("\n\n")

                .append("<b>CLÁUSULA QUARTA - DA MULTA <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></b>").append("\n")
                .append("A desistência do presente contrato causara uma multa de 30% do valor ficado a pagar a parte ofendida, por motivos de segurança de ambas as partes.").append("\n\n")

                .append("<b>CLÁUSULA QUINTA - ? <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></b>").append("\n\n")

                .append("<b>CLÁUSULA SEXTA - OBJETOS DEIXADOS NO VEÍCULO <font color='white'> _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></b>").append("\n")
                .append("6.1 - <b>A CONTRATADA</b> não se responsabiliza por quaisquer objetos e/ou valores deixados ou esquecidos no veículo.").append("\n\n")

                .append("<b>CLÁUSULA SETIMA - DECLARAÇÕES FINAIS <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></b>").append("\n")
                .append("7.1 - <b>A CONTRATANTE</b> concorda e declara ter plena ciência dos presentes termos e condições do veículo").append("\n")
                .append("7.2 - Ter pleno conhecimento dos acessórios e equipamentos do veículo locado, inclusive para utilizálos em situações de emergência.").append("\n")
                .append("7.3 - Serem verídicos e válidos todos os documentos e informações pretados a <b>CONTRATANTE</b> inclusive as originais de identidade e habilitação de todos os condutores.").append("\n\n")

                .append("<b>CLÁUSULA OITAVA - DA RECISÃO DO CONTRATO <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></b>").append("\n")
                .append("8.1 - O não cumprimento de quaisquer das condições estipuladas neste contrato implicará o seu cancelamento junto com a execução da CLÁUSULA QUARTA").append("\n\n")

                .append("<b>CLÁUSULA NONA - DO FORO <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></b>").append("\n")
                .append("9.1 - Fica eleito o Foro da Comarca de " + f.getEmpresa().getCidade().getNome() + " / " + f.getEmpresa().getCidade().getEstado().getIniciais() + "para solução de questões jurídicas oriundas deste contrato")
                .append("\n\n\n\n")


                .append("<font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _  </font>" +f.getEmpresa().getCidade().getNome() +", " + Utils.getDataPorExtenso(f.getDataContratacao())).append("\n\n\n\n")

                .append("______________________________________________").append("\n")
                .append("CONTRATADA: " + f.getEmpresa().getNome()).append("\n")
                .append("CNPJ: " + f.getEmpresa().getPessoaJuridica().getCnpjFormatado()).append("\n\n\n\n")

                .append("______________________________________________").append("\n")
                .append("CONTRATANTE: " + f.getCliente().getNome()).append("\n")
                .append(f.getCliente().getTipo().equals(PessoaTipo.FISICA) ? "CPF: " + f.getCliente().getPessoaFisica().getCpfFormatado(): "CNPJ: " + f.getCliente().getPessoaJuridica().getCnpjFormatado())
                .append("\n\n\n\n")

                .append("______________________________________________").append("\n")
                .append("Testemunha 1 - Nome: ").append("\n")
                .append("CPF:   ")
                .append("\n\n\n\n")

                .append("______________________________________________").append("\n")
                .append("Testemunha 2 - Nome: ").append("\n")
                .append("CPF: ");

        return contrato.toString();
    }
}
