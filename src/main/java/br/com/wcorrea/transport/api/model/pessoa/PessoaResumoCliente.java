package br.com.wcorrea.transport.api.model.pessoa;

import lombok.Data;

@Data
public class PessoaResumoCliente {

    private String key;
    private PessoaTipo pessoaTipo;
    private String cpfcnpj;
    private String rgInscricaoEstadual;
    private String nomeRazaoSocial;
    private String apelidoFantasia;
    private String fotoLogo;
    private String cidadekey;
    private String endereco;
    private String bairro;
    private String email;
    private String telefone1;
    private String telefone1Obs;
    private String telefone2;
    private String telefone2Obs;
    private String obs;

    private PessoaResumoCliente() {
    }

}
