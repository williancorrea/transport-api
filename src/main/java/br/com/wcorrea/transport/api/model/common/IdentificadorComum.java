package br.com.wcorrea.transport.api.model.common;

import br.com.wcorrea.transport.api.service.exception.ExceptionCriptografarKey;
import br.com.wcorrea.transport.api.service.exception.ExceptionDescriptografarKey;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode
@MappedSuperclass
public abstract class IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 7988485287160781269L;

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
    private PropriedadesComuns controle;


    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new ExceptionDescriptografarKey();
        }
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new ExceptionCriptografarKey();
        }
    }

    @JsonIgnore
    @Transient
    public boolean isEditando() {
        return id != null ? true : false;
    }

    @PrePersist
    public void prePersist() {
        this.controle = new PropriedadesComuns();
    }

    @PreUpdate
    public void preUpdate() {
        this.getControle().setDataCriacao(new Date());
    }
}
