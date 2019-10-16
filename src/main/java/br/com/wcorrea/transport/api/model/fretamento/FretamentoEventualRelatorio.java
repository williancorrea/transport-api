package br.com.wcorrea.transport.api.model.fretamento;

import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
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

        contrato.append("\n\n")
                .append("<style size='12' isBold='true'><font color='white'>_ _ </font> CONTRATO DE PRESTAÇÃO DE SERVIÇO DE TRANSPORTE DE <font color='white'> _ _ _ _ </font>")
                .append("\n")
                .append("<font color='white'>_ _ _ _ _ _ </font> PASSAGEIROS, SOB REGIME DE FRETAMENTO. </style>")
                .append("\n\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA PRIMEIRA - DO OBJETO <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></style>")
                .append("\n")
                .append("1 - O presente contrato tem por finalidade a prestação de serviços de transporte rodoviário intermunicipal de passageiros, sob o regime de fretamento eventual, que será realizado conforme descrição abaixo.")
                .append("\n")
                .append("1.1 - O veículo que será utilizado ma prestação de serviço será de marca, **Mercedes O 500 RS**, modelo **Paradiso 1.200 g6**, Frota **15.000**, com a capacidade de **50** lugares,  viagens a ser realizadas conforme descrição abaixo.")
                .append("\n")
                .append("1.2 - A viagem terá como origem a cidade de Assis – SP, com a data de saída 27/10/2019, horário de saída: 07h00, local de Saída: Batalhão da policia militar, e terá como destino, Aparecida do Norte-SP, data de retorno 29/10/2019, horário de retorno, 16h00.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA SEGUNDA - DA VIGÊNCIA <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("2 - A vigência do presente contrato será a partir da data de assinatura do contrato, até a data de finalização da viagem, podendo ser prorrogado mediante acordo entre as partes. ")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA TERCEIRA – CONDIÇÕES COMERCIAIS <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("3 - A CONTRATANTE pagará à CONTRATADA, pela execução dos serviços:")
                .append("\n")
                .append("3.1 - Valor fixo integral de R$ 125,00 (Cento e Vinte e Cinco Reais) que deverá ser pago ate um dia antes da realização da viagem através por boleto, deposito bancário, ou até mesmo diretamente no escritório oficial no endereço da CONTRATANTA.")
                .append("\n")
                .append("3.2 - Banco para depósito (Caixa econômica Federal: Agencia 0901 – Conta Corrente: 1328-8) ")
                .append("\n")
                .append("3.3 - Caso seja necessário realizar alguma alteração na rota destinada por conta da CONTRATANTE, deveram ser acordados e comunicado com antecedência CONTRATADA de no mínimo 7 dias antes da realização da viagem.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLAUSULA QUARTA - DO REAJUSTE <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("4 - Os preços ora contratados, não serão reajustados até o final da vigência do contrato, salvo clausula 3.3.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA QUINTA - DAS OBRIGAÇÕES E DAS RESPONSABILIDADES DA CONTRATADA</style>")
                .append("\n")
                .append("5 - É de responsabilidade da CONTRATADA o fiel cumprimento de todos os serviços acordados, tal como ajustados neste contrato, ressalvadas as hipóteses de imprevistos oriundos de caso fortuito e força maior que possa ocorrem.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA SEXTA - DAS OBRIGAÇÕES E DAS RESPONSABILIDADES DA CONTRATANTE</style>")
                .append("\n")
                .append("6 - O LOCATÁRIO deverá apresentar com dois dias de antecedência da viagem lista com o nome completo e número de RG de todos os passageiros.")
                .append("\n")
                .append("6.2 - O CONTRATANTE se obriga a fazer bom uso do veículo, sendo responsabilizado caso haja danos no estofamento, janelas, vidraças, falta de qualquer acessório disponibilizados na viagem entre outros, deverá arcando financeiramente pelo dano causado, mediante a apresentação de nota fiscal de conserto, bem como se obrigam terem comportamento adequado, tratando uns aos outros com civilidade, solidariedade, urbanismo e respeito")
                .append("\n")
                .append("6.3 - O CONTRATANTE e usuários do transportes não poderá fazer uso de bebida alcoólica, cigarro ou outro produto nocivo a saúde ou proibido por lei, dentro do ônibus.")
                .append("\n")
                .append("6.4 - O CONTRATANTE fica ciente que somente será permitido o transporte de passageiros limitados à capacidade de passageiros sentados no veículo utilizado, ficando expressamente proibido o transporte de passageiros em pé ou acomodados no corredor, bem como passageiros que não estiverem constando na relação de passageiros, caso seja menor de 5 anos, o mesmo deverá fazer uso de cadeirinhas apropriadas e regulamentada pela legislação vigente (bebe conforto) não fornecidas pela empresa.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA SETIMA – OBJETOS DEIXADOS NO VEÍCULO <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("7 - A CONTRATADA não se responsabiliza por quaisquer objetos e/ou valores deixados ou esquecidos no veículo, porem caso a empresa venha encontrar o mesmo será guardado pelo prazo de 15 dias no setor de achado e perdidos, caso não tenha nenhuma procura será doado para alguma instituição carente do município.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLAUSULA OITAVA – DESPESAS DECORRENTES <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("8 - Todas as despesas decorrentes a encargos tributários com o ICMS, IPI, ISS, PIS, COFINS, INSS, a mão de obra, manutenção entre outros que estiverem ligadas ao objeto da prestação de serviço serão de responsabilidade da CONTRADA.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLAUSULA NONA - DAS CONDIÇÕES DE RESCISÃO <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("9 - O presente contrato poderá ser rescindido de pleno direito, nas seguintes hipóteses:")
                .append("\n")
                .append("9.1 - Se o CONTRATADO ou o CONTRATANTE  não cumprir qualquer obrigação ajustada no presente contrato.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA DÉCIMA – MULTA <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("10 - A desistência do presente contrato causara uma multa de 30% do valor fixado a pagar a parte ofendida, por motivos de segurança de ambas as partes.")
                .append("\n")
                .append("10.1 - Por ventura se o veículo contratado esteja impossibilitado de realizar o fretamento por força maior. Exemplo: Problema mecânico, pane elétrica dentre outros, o mesmo poderá substituído por outro veículo com a mesma capacidade de passageiros, caso a CONTRATANTE, não esteja de acordo com a substituição a mesma poderá rescindir o contrato sem nenhuma penalidade. ")
                .append("\n")
                .append("10.2 - Caso ocorra qualquer autuação por culpa da contratada ou passageiros que utilizaram o transporte, exemplo, falta de uso se cinto de segurança, passageiros fora da lista apresentada entre outros, a contratante deverá assumir esse prejuízo.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA DÉCIMA PRIMEIRA – CONFIDENCIALIDADE <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _  </font></style>")
                .append("\n")
                .append("11 - A CONTRATADA e CONTRATANTE se compromete a não utilizar os elementos comerciais, técnicos ou jurídicos que integram o presente contrato ou que serviram de base para a sua constituição a título de propaganda ou marketing, em caráter público ou reservado, seja em forma escrita ou visual.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA DÉCIMA SEGUNDA – CONDIÇÕES GERAIS <font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ </font></style>")
                .append("\n")
                .append("12 - O CONTRATANTE concorda e declara ter plena ciência dos presentes termos e condições do veículo visto e aceito.")
                .append("\n")
                .append("12.1 - Ter pleno conhecimento dos acessórios e equipamentos do veículo contratado, inclusive para utilizá-los em situações de emergência.")
                .append("\n")
                .append("12.2 - Serem verídicos e válidos todos os documentos e informações prestadas para realização deste contrato.")
                .append("\n\n\n")

                .append("<style size='10' isBold='true'>CLÁUSULA DÉCIMA TERCEIRA – DO FORO <font color='white'> _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _ _</font></style>")
                .append("\n")
                .append("13 - As partes elegem o foro da cidade de Paraguaçu Paulista no estado do SP, como o único competente para dirimir quaisquer dúvidas ou questões oriundas do presente contrato, renunciando expressamente a qualquer outro, por mais privilegiado que seja.")
                .append("\n")
                .append("13.1 - E, por assim estarem justas e contratadas, as partes mandaram redigir o presente Instrumento Particular de Contrato de Prestação de Serviços de Transporte de Passageiros (Fretamento), em 03 (três) vias de igual teor e forma, destinando-se 01 (uma) via para a CONTRATANTE e as restantes para o CONTRATADO,   que assinam juntamente com as 02 (duas) testemunhas abaixo, a tudo presentes.")
                .append("\n\n\n")


                .append("<font color='white'>_ _ _ _ _ _ _ _ _ _ _ _ _ _  </font>" +f.getEmpresa().getCidade().getNome() +", " + Utils.getDataPorExtenso(f.getDataImpressaoContrato()))
                .append("\n\n\n\n")

                .append("______________________________________________").append("\n")
                .append("CONTRATADA: " + f.getEmpresa().getNome()).append("\n")
                .append("CNPJ: " + f.getEmpresa().getPessoaJuridica().getCnpj()).append("\n\n\n\n")

                .append("______________________________________________").append("\n")
                .append("CONTRATANTE: " + f.getCliente().getNome()).append("\n")
                .append(f.getCliente().getTipo().equals(PessoaTipo.FISICA) ? "CPF: " + f.getCliente().getPessoaFisica().getCpf(): "CNPJ: " + f.getCliente().getPessoaJuridica().getCnpj())
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

    /**
     * Classe utilizada para gerar o Termo de Responsabilidade de motorista
     */
    public static class TermoResponsabilidadeMotorista{

        private Pessoa motorista;
        public TermoResponsabilidadeMotorista() {
        }

        public TermoResponsabilidadeMotorista(Pessoa motorista) {
            this.motorista = motorista;
        }

        public Pessoa getMotorista() {
            return motorista;
        }
    }
}
