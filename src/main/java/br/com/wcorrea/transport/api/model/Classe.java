package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.BankNotFound;
import br.com.wcorrea.transport.api.service.exception.ClasseNaoEncontrada;
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
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "classe")
public class Classe implements Serializable {
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
    private PropriedadesComuns controle;

    @NotBlank
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String descricao;

    @Getter
    @Setter
    private boolean inativo;

    public Classe() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new ClasseNaoEncontrada();
        }
    }

    @Transient
    public void setKey(String key) throws Exception {
        if (id != null) {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        }
    }

    @PrePersist
    public void prePersist() {
        this.controle = new PropriedadesComuns();
    }

}
