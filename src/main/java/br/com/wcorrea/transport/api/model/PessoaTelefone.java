package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.service.exception.PessoaTelefoneNaoEncontrado;
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

@ToString
@EqualsAndHashCode
@Entity(name = "pessoa_telefone")
public class PessoaTelefone {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @Getter
    @Setter
    @NotBlank
    @Column(name = "telefone_tipo")
    @Enumerated
    private TelefoneTipo telefoneTipo;

    @Getter
    @Setter
    @Size(max = 14)
    private String numero;

    @Getter
    @Setter
    @Size(max = 250)
    private String observacao;

    @Getter
    @Setter
    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne
    private Pessoa pessoa;

    public PessoaTelefone() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new PessoaTelefoneNaoEncontrado();
        }
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new PessoaTelefoneNaoEncontrado();
        }
    }
}
