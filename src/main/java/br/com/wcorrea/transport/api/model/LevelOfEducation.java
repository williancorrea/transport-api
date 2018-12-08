package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.NivelEducacaoNaoEncontrado;
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
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "level_of_education")
public class LevelOfEducation implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    @Getter
    @Setter
    private Long id;

    @Embedded
    @Getter
    @Setter
    private PropriedadesComuns properties;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String name;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String description;

    @Column(name = "degree_of_instruction_caged")
    @Getter
    @Setter
    private Long degreeOfInstructionCaged;

    @Column(name = "degree_of_instruction_sefip")
    @Getter
    @Setter
    private Long degreeOfInstructionSefip;

    @Column(name = "degree_of_instruction_rais")
    @Getter
    @Setter
    private Long degreeOfInstructionRais;

    public LevelOfEducation() {
    }

    @Transient
    public String getKey(){
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new NivelEducacaoNaoEncontrado();
        }
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new NivelEducacaoNaoEncontrado();
        }
    }

    @PrePersist
    public void prePersist() {
        this.properties = new PropriedadesComuns();
    }

    @PreUpdate
    public void preUpdade() {
        this.properties.setDataAlteracao(new Date());
    }
}
