package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.veiculo.ControleKmNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "controle_km")
public class ControleKm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Getter
    @Setter
    private Long id;

    @Embedded
    @Getter
    @Setter
    private PropriedadesComuns controle;

    @JoinColumn(name = "veiculo_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    @NotNull
    private Veiculo veiculo;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    @NotNull
    private Pessoa pessoa;

    @JoinColumn(name = "itinerario_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    @NotNull
    private Itinerario itinerario;

    @Getter
    @Setter
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "data_hora_saida")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraSaida;

    @Getter
    @Setter
    @NotNull
    @Column(name = "data_hora_chegada")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraChegada;

    @NotNull
    @Size(min = 3, max = 150)
    @Getter
    @Setter
    private String origem;

    @NotNull
    @Size(min = 3, max = 150)
    @Getter
    @Setter
    private String destino;

    @NotNull
    @Getter
    @Setter
    @Column(name = "km_saida")
    private Long kmSaida;

    @NotNull
    @Getter
    @Setter
    @Column(name = "km_chegada")
    private Long kmChegada;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String obs;

    @Transient
    @Getter
    @Setter
    private Long kmNaoInformado;

    public ControleKm() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new ControleKmNaoEncontrado();
        }
    }

    @Transient
    public Long getKmTotal() {
        return this.kmChegada - this.kmSaida;
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new ControleKmNaoEncontrado();
        }
    }

    @PrePersist
    public void prePersist() {
        this.controle = new PropriedadesComuns();
    }

    @PreUpdate
    public void preUpdade() {
        this.controle.setDataAlteracao(new Date());
    }
}
