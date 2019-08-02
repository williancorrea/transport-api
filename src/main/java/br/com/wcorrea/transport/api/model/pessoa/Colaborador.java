package br.com.wcorrea.transport.api.model.pessoa;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ToString
@Entity(name = "colaborador")
@Data
public class Colaborador extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 6855045965253378941L;

    @JsonIgnoreProperties("pessoa")
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @NotNull
    private Pessoa pessoa;

    @NotBlank
    @Size(max = 10)
    private String matricula;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_cadastro")
    private Date dataCadastro;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_admissao")
    @NotNull
    private Date dataAdmissao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_demissao")
    private Date dataDemissao;

    @Lob
    @NotBlank
    private String observacao;
}
