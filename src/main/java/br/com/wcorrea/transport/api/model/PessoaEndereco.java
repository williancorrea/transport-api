package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.service.exception.PessoaEnderecoNaoEncontrado;
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

@ToString
@EqualsAndHashCode
@Entity(name = "pessoa_endereco")
public class PessoaEndereco implements Serializable {
    private static final long serialVersionUID = 8059136906512768883L;

    @Getter
    @Setter
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @Size(max = 150)
    private String logradouro;

    @Getter
    @Setter
    @Size(max = 15)
    private String numero;

    @Getter
    @Setter
    @Size(max = 80)
    private String complemento;

    @Setter
    @Getter
    @Size(max = 100)
    private String bairro;

    @Getter
    @Setter
    @Size(max = 8)
    private String cep;

    @Getter
    @Setter
    private Boolean principal;

    @Getter
    @Setter
    private Boolean entrega;

    @Getter
    @Setter
    private Boolean cobranca;

    @Getter
    @Setter
    private Boolean correspondencia;

    @Getter
    @Setter
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    @ManyToOne()
    private Cidade cidade;

    @Getter
    @Setter
    @NotNull
    @JoinColumn(name = "pessoa_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Pessoa pessoa;

    public PessoaEndereco() {
    }

    @Transient
    public String getKey() {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            throw new PessoaEnderecoNaoEncontrado();
        }
    }

    @Transient
    public void setKey(String key) {
        try {
            this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
        } catch (Exception e) {
            throw new PessoaEnderecoNaoEncontrado();
        }
    }
}
