package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.service.exception.EstadoCivilNaoEncontrado;
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

/**
 * Classe responsavel por manipular todos os atributos de um objeto do tipo Estado
 * Created by Willian Vagner Vicente Corrêa (willian.vag@gmail.com) on 19/08/17.
 */
@ToString
@EqualsAndHashCode
@Entity(name = "estado")
public class Estado implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Size(min = 3, max = 150)
    @Getter
    @Setter
    private String descricao;

    @NotNull
    @Size(min = 2, max = 2)
    @Getter
    @Setter
    private String iniciais;

    @Column(name = "codigo_ibge")
    @Getter
    @Setter
    private Integer codigoIbge;

    public Estado() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new EstadoCivilNaoEncontrado();
        }
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new EstadoCivilNaoEncontrado();
        }
    }
}
