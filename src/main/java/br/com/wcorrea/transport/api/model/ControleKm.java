package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.pessoa.Pessoa;
import br.com.wcorrea.transport.api.model.veiculo.Veiculo;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "controle_km")
@Data
public class ControleKm extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    //TODO:Adicionar os atributos  a serem ignorados, para diminuir a carga de dados a ser enviada pelo json
    private Veiculo veiculo;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    //TODO:Adicionar os atributos  a serem ignorados, para diminuir a carga de dados a ser enviada pelo json
    private Pessoa pessoa;

    @JoinColumn(name = "itinerario_id", referencedColumnName = "id")
    @ManyToOne()
    @NotNull
    //TODO:Adicionar os atributos  a serem ignorados, para diminuir a carga de dados a ser enviada pelo json
    private Itinerario itinerario;

    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "data_hora_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraSaida;

    @NotNull
    @Column(name = "data_hora_chegada")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraChegada;

    @NotNull
    @Size(min = 3, max = 150)
    private String origem;

    @NotNull
    @Size(min = 3, max = 150)
    private String destino;

    @NotNull
    @Column(name = "km_saida")
    private Long kmSaida;

    @NotNull
    @Column(name = "km_chegada")
    private Long kmChegada;

    @Size(max = 512)
    @Lob
    private String obs;

    @Transient
    private Long kmNaoInformado;

    public ControleKm() {
    }

    @Transient
    public Long getKmTotal() {
        return this.kmChegada - this.kmSaida;
    }
}
