package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.utils.Cryptography;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;

@ToString
@EqualsAndHashCode
@Entity(name = "pessoa_fisica")
public class PessoaFisica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    @Getter
    @Setter
    private Long id;

    @NotBlank
    @Size(min = 14, max = 14)
    @Getter
    @Setter
    @CPF
    private String cpf;

    @Size(max = 15)
    @Getter
    @Setter
    private String rg;

    @Column(name = "orgao_rg")
    @Size(max = 6)
    @Getter
    @Setter
    private String orgaoRg;

    @Column(name = "data_emissao_rg")
    @Getter
    @Setter
    private LocalDate dataEmissaoRg;

    @Column(name = "data_nascimento")
    @Getter
    @Setter
    private LocalDate dataNascimento;

    @Column(name = "sexo")
    @Size(min = 1, max = 1)
    @Getter
    @Setter
    private String sexo;

    @Column(name = "naturalidade")
    @Size(max = 250)
    @Getter
    @Setter
    private String naturalidade;

    @Column(name = "nacionalidade")
    @Size(max = 250)
    @Getter
    @Setter
    private String nacionalidade;

    @Column(name = "tipo_sangue")
    @Size(max = 5)
    @Getter
    @Setter
    private String tipoSangue;

    @Column(name = "cnh_numero")
    @Size(max = 30)
    @Getter
    @Setter
    private String cnhNumero;

    @Column(name = "cnh_categoria")
    @Size(max = 2)
    @Getter
    @Setter
    private String cnhCategoria;

    @Column(name = "cnh_vencimento")
    @Getter
    @Setter
    private LocalDate cnhVencimento;

    @Column(name = "titulo_eleitoral_numero")
    @Size(max = 30)
    @Getter
    @Setter
    private String tituloEleitoralNumero;

    @Column(name = "titulo_eleitoral_zona")
    @Size(max = 3)
    @Getter
    @Setter
    private String tituloEleitoralZona;

    @Column(name = "titulo_eleitoral_secao")
    @Size(max = 10)
    @Getter
    @Setter
    private String tituloEleitoralSecao;

    @Column(name = "reservista_numero")
    @Size(max = 30)
    @Getter
    @Setter
    private String reservistaNumero;

    @Column(name = "reservista_categoria")
    @Size(max = 50)
    @Getter
    @Setter
    private String reservistaCategoria;

    @Column(name = "nome_mae")
    @Size(max = 250)
    @Getter
    @Setter
    private String nomeMae;

    @Column(name = "nome_pai")
    @Size(max = 250)
    @Getter
    @Setter
    private String nomePai;


    @JoinColumn(name = "estado_civil_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    private MaritalStatus estadoCivil;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Pessoa pessoa;

    public PessoaFisica() {
    }

    @Transient
    public String getKey() throws Exception {
        return this.id != null ? new Cryptography().encryptToHex(this.id.toString()) : null;
    }

    @Transient
    public void setKey(String key) throws Exception {
        if (id != null) {
            this.id = Long.parseLong(new Cryptography().decryptFromHex(key));
        }
    }
}
