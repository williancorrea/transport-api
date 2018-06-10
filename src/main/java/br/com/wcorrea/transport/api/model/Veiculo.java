package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.VeiculoNaoEncontrado;
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
import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Entity(name = "veiculo")
public class Veiculo implements Serializable {
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
    @Size(min = 8, max = 8)
    @Getter
    @Setter
    private String placa;

    @Size(max = 15)
    @Getter
    @Setter
    private String frota;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String obs;

    public Veiculo() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new VeiculoNaoEncontrado();
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
