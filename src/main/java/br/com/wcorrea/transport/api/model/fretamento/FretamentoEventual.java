package br.com.wcorrea.transport.api.model.fretamento;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.utils.Utils;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "fretamento_eventual")
@Data
public class FretamentoEventual extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 6855045965253378941L;

    @NotNull
    @Enumerated(EnumType.STRING)
    private FretamentoEventalTipo situacao;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "situacao_data")
    private Date situacaoData;

    @JoinColumn(name = "pessoa_cliente_id", referencedColumnName = "id")
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    private Pessoa cliente;

    @Embedded
    @Valid
    private FretamentoEventualContato contato;

    @NotNull
    @Embedded
    @Valid
    private FretamentoEventualItinerario itinerario;

    @NotNull
    @Embedded
    @Valid
    private FretamentoEventualCusto custo;

    @NotNull
    @JoinColumn(name = "empresa_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa empresa;

    @NotNull
    @JoinColumn(name = "empresa_representante_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa representanteComercial;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    @Column(name = "data_impressao_contratato")
    private Date dataImpressaoContrato;

    @Column(name = "numero_contrato")
    private String numeroContrato;

    public String getNumeroContrato() {
        return Utils.StrZeroEsquerda(this.getId().toString(), 5);
    }

    @PostPersist
    public void postPersist() {
        this.numeroContrato = Utils.StrZeroEsquerda(this.getId().toString(), 5);
    }

    @JsonIgnore
    @Transient
    public boolean isContratado() {
        return situacao.equals(FretamentoEventalTipo.CONTRATADO) ? true : false;
    }
}
