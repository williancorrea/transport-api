package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.service.exception.PessoaContatoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@ToString
@EqualsAndHashCode
@Entity(name = "pessoa_contato")
public class PessoaContato {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Getter
    @Setter
    @Size(max = 150)
    private String nome;

    @Getter
    @Setter
    @Size(max = 250)
    private String email;

    @Getter
    @Setter
    @Size(max = 14)
    private String foneComercial;
    @Getter
    @Setter
    @Size(max = 14)
    private String foneResidencial;
    @Getter
    @Setter
    @Size(max = 14)
    private String foneCelular;

    @Getter
    @Setter
    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public PessoaContato() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new PessoaContatoNaoEncontrado();
        }
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new PessoaContatoNaoEncontrado();
        }
    }
}
