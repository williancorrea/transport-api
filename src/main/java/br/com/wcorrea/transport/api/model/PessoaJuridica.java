package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "pessoa_juridica")
public class PessoaJuridica implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    @Getter
    @Setter
    private Long id;

    @NotBlank
    @Size(min = 18, max = 18)
    @Getter
    @Setter
    @CNPJ
    private String cnpj;

    @Column(name = "fantasia")
    @Size(max = 250)
    @Getter
    private String fantasia;

    @Column(name = "inscricao_municipal")
    @Size(max = 50)
    @Getter
    @Setter
    private String inscricaoMunicipal;

    @Column(name = "inscricao_estadual")
    @Size(max = 50)
    @Getter
    @Setter
    private String inscricaoEstadual;

    @Column(name = "tipo_regime")
    @Size(max = 20)
    @Getter
    @Setter
    private String tipoRegime;

    // Código de regime tributário: Simples Nacional
    @Column(name = "crt")
    @Size(max = 50)
    @Getter
    @Setter
    private String crt;

    // Superintendência da Zona Franca de Manaus
    @Column(name = "suframa")
    @Size(max = 50)
    @Getter
    @Setter
    private String suframa;

    @Getter
    @Setter
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_constituicao")
    private Date dataConstituicao;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @Getter
    @Setter
    private Pessoa pessoa;

    public PessoaJuridica() {
    }

    @Transient
    public String getKey() throws Exception {
        return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
    }

    @Transient
    public void setKey(String key) throws Exception {
        if (id != null) {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        }
    }

    public void setFantasia(String fantasia) {
        if (fantasia != null) {
            this.fantasia = fantasia.toUpperCase();
        }
    }
}
