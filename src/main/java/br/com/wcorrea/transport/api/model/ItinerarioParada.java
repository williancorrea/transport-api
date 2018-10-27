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
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "Itinerario_parada")
public class ItinerarioParada implements Serializable {
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

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String nome;

    @Size(max = 15)
    @Getter
    @Setter
    private String codigo;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String descricao;

    @JoinColumn(name = "itinerario_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    private Itinerario itinerario;

    public ItinerarioParada() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
//            throw new ItinerarioPararadaNaoEncontrado;
            //todo: corrigir
            return null;
        }
    }

    @Transient
    public void setKey(String key) throws Exception {
        //todo corrigir
        this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
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
