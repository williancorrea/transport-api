package br.com.wcorrea.transport.api.model.seguranca;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Gerencia as permissoes do usuario que tera acesso ao sistema
 */
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "permissao")
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Size(min = 5, max = 250)
    @Getter
    @Setter
    private String descricao;

    public Permissao() {
    }
}
