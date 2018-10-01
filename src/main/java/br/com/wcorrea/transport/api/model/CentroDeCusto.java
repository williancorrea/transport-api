package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.CentroDeCustoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@ToString
@EqualsAndHashCode
@Entity(name = "CentroDeCusto")
public class CentroDeCusto implements Serializable {
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

    @NotNull
    @JoinColumn(name = "classe_despeza_id", referencedColumnName = "id")
    @ManyToOne()
    @Getter
    @Setter
    private ClasseDespeza classeDespeza;

    public CentroDeCusto() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new CentroDeCustoNaoEncontrado();
        }
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new CentroDeCustoNaoEncontrado();
        }
    }

    @PrePersist
    public void prePersist() {
        this.controle = new PropriedadesComuns();
    }

}
