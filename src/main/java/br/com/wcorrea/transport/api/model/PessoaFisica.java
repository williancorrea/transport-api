package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "pessoa_fisica")
@Data
public class PessoaFisica extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 14, max = 14)
    @CPF
    private String cpf;

    @Size(min = 5, max = 12)
    private String rg;

    @Size(max = 100)
    private String apelido;

    @Column(name = "orgao_rg")
    @Size(max = 6)
    private String orgaoRg;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissao_rg")
    private Date dataEmissaoRg;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

    @Column(name = "sexo")
    @Size(min = 1, max = 1)
    private String sexo;

    @Column(name = "naturalidade")
    @Size(max = 250)
    private String naturalidade;

    @Column(name = "nacionalidade")
    @Size(max = 250)
    private String nacionalidade;

    @Column(name = "tipo_sangue")
    @Size(max = 5)
    private String tipoSangue;

    @Column(name = "cnh_numero")
    @Size(max = 30)
    private String cnhNumero;

    @Column(name = "cnh_categoria")
    @Size(max = 2)
    private String cnhCategoria;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "cnh_vencimento")
    private Date cnhVencimento;

    @Column(name = "titulo_eleitoral_numero")
    @Size(max = 30)
    private String tituloEleitoralNumero;

    @Column(name = "titulo_eleitoral_zona")
    @Size(max = 3)
    private String tituloEleitoralZona;

    @Column(name = "titulo_eleitoral_secao")
    @Size(max = 10)
    private String tituloEleitoralSecao;

    @Column(name = "reservista_numero")
    @Size(max = 30)
    private String reservistaNumero;

    @Column(name = "reservista_categoria")
    @Size(max = 50)
    private String reservistaCategoria;

    @Column(name = "nome_mae")
    @Size(max = 250)
    private String nomeMae;

    @Column(name = "nome_pai")
    @Size(max = 250)
    private String nomePai;


    @JoinColumn(name = "estado_civil_id", referencedColumnName = "id")
    @ManyToOne()
    private EstadoCivil estadoCivil;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public PessoaFisica() {
    }
}
