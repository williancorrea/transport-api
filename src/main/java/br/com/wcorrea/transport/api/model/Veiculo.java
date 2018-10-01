package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.veiculo.VeiculoNaoEncontrado;
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
    private String placa;

    @Size(max = 15)
    @Getter
    private String frota;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String obs;

    @Getter
    @Column(name = "odometro_inicial")
    private Long odometroInicial;

    public Veiculo() {
    }

    public void setPlaca(String placa) {
        this.placa = placa.toUpperCase();
    }

    public void setFrota(String frota) {
        this.frota = frota.toUpperCase();
    }

    public void setOdometroInicial(Long odometroInicial) {
        this.odometroInicial = odometroInicial == null ? 0 : odometroInicial;
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
    public void setKey(String key){
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new VeiculoNaoEncontrado();
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
