package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.BankNotFound;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Entity(name = "bank")
public class Bank implements Serializable {
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

    @Size(max = 10)
    @Getter
    @Setter
    private String code;

    @NotBlank
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String name;

    @Size(max = 250)
    @Getter
    @Setter
    private String url;

    public Bank() {
    }

    @Transient
    public String getKey(){
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new BankNotFound();
        }
    }

    @Transient
    public void setKey(String key){
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new BankNotFound();
        }
    }

    @PrePersist
    public void prePersist() {
        this.properties = new PropriedadesComuns();
    }
}
