package br.com.wcorrea.transport.api.model.seguranca;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Gerencia as permissoes do usuario que tera acesso ao sistema
 */
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "permissao")
@Data
public class Permissao implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id")
    private Long id;

    @NotNull
    @Size(min = 5, max = 100)
    private String nome;

    @NotNull
    @Size(min = 5, max = 250)
    private String descricao;

    public Permissao() {
    }
}
