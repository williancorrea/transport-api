package br.com.wcorrea.transport.api.model.pessoa;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.utils.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import javax.swing.text.MaskFormatter;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "pessoa_juridica")
@Data
public class PessoaJuridica extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 14, max = 18)
    @CNPJ
    private String cnpj;

    @Column(name = "inscricao_municipal")
    @Size(max = 50)
    private String inscricaoMunicipal;

    @Column(name = "inscricao_estadual")
    @Size(max = 50)
    private String inscricaoEstadual;

    @Column(name = "tipo_regime")
    @Size(max = 20)
    private String tipoRegime;

    // Código de regime tributário: Simples Nacional
    @Column(name = "crt")
    @Size(max = 50)
    private String crt;

    // Superintendência da Zona Franca de Manaus
    @Column(name = "suframa")
    @Size(max = 50)
    private String suframa;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_constituicao")
    private Date dataConstituicao;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public PessoaJuridica() {
    }

    public String getCnpjFormatado() {
        return Utils.formatarValor(this.cnpj, "##.###.###/####-##");
    }

    public String getInscricaoEstadualFormatada() {
        return Utils.formatarValor(this.inscricaoEstadual, "###.###.###.####");
    }
}
