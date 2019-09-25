package br.com.wcorrea.transport.api.model.fretamento;

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

        contrato.append("<style size='12' isBold='true' forecolor='black'>\n              CONTRATO PARTICULAR DE LOCAÇÃO DE VEÍCULO</style>").append("\n\n");

        contrato.append("Pelo presente instrumento particular de contrato, de um lado <b>" + f.getEmpresa().getNome()+"</b>, com sede à Rua " + f.getEmpresa().getEndereco() + " - " + f.getEmpresa().getBairro()
                + " na cidade de " + f.getEmpresa().getCidade().getNome() +" / "+ f.getEmpresa().getCidade().getEstado().getIniciais() +", inscrita no CNPJ sob o n.º "+ f.getEmpresa().getPessoaJuridica().getCnpjFormatado()
                + " I.E. " + f.getEmpresa().getPessoaJuridica().getInscricaoEstadualFormatada() + ", doravante simplesmente denominada CONTRATANTE e de outro lado o Sr.(a) XXXXXXXX, " +
                "com sede no Município de XXXXXXX/XX, na Rua. XXXXXX Nº XXXX – Vila Nova, inscrita no" +
                " CNPJ/MF sob o n.º 26.166.778/0001-69, Insc. Estadual 503.062.632.116, doravante denominada simplesmente CONTRATADA, têm entre si, como justo e pactuado, a contratação dos" +
                " serviços de transporte de pessoas, mediante as cláusulas e condições seguintes:");
        return contrato.toString();
    }
}
