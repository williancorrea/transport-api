package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Entity(name = "controle_km")
public class ControleKM implements Serializable {
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
    private Veiculo veiculo;

    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    private Pessoa pessoa;

    @JoinColumn(name = "itinerario_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    private Itinerario itinerario;

    @Getter
    @Setter
    @Column(name = "data")
    private LocalDate data;

    @Getter
    @Setter
    @Column(name = "hora_saida")
    private LocalDateTime horaSaida;

    @Getter
    @Setter
    @Column(name = "hora_chegada")
    private LocalDateTime horaChegada;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String origem;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String destino;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    @Column(name = "km_saida")
    private String kmSaida;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    @Column(name = "km_chegada")
    private String kmChegada;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String obs;

    public ControleKM() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Transient
    public void setKey(String key) throws Exception {
        this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
    }

    @PrePersist
    public void prePersist() {
        this.controle = new PropriedadesComuns();
    }

    @PreUpdate
    public void preUpdade() {
        this.controle.setDataAlteracao(LocalDateTime.now());
    }
}
