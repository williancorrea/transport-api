package br.com.wcorrea.transport.api.model.common;

import br.com.wcorrea.transport.api.service.exception.ExceptionDescriptografarKey;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@MappedSuperclass
@Data
public abstract class IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 7988485287160781269L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    @JsonIgnore
    private Long id;

//    @Embedded
//    private PropriedadesComuns controle;


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
            if (key != null) {
                this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
            } else {
                this.id = null;
            }
        } catch (Exception e) {
            /*
             * Estava apresentando erro de parse para o usuario assim que recebia o objeto
             */
            this.id = (long) -1;
        }
    }

    @JsonIgnore
    @Transient
    public boolean isEditando() {
        return id != null;
    }
}
