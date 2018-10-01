package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.service.exception.PessoaFisicaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

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

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_emissao_rg")
    private Date dataEmissaoRg;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_nascimento")
    private Date dataNascimento;

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

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "cnh_vencimento")
    private Date cnhVencimento;

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
    private EstadoCivil estadoCivil;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Pessoa pessoa;

    public PessoaFisica() {
    }

    @Transient
    public String getKey(){
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new PessoaFisicaNaoEncontrada();
        }
    }

    @Transient
    public void setKey(String key){
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new PessoaFisicaNaoEncontrada();
        }
    }
}
