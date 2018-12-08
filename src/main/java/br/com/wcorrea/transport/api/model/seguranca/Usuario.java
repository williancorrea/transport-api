package br.com.wcorrea.transport.api.model.seguranca;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Usuario do sistema
 */
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    @Setter
    private Long id;

    @NotNull
    @Size(min = 5, max = 250)
    @Getter
    @Setter
    private String nome;

    @NotNull
    @Email
    @Size(min = 5, max = 200)
    @Getter
    @Setter
    private String email;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String senha;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "usuario_permissao",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_permissao"))
    @Getter
    @Setter
    private List<Permissao> permissoes;

    public Usuario() {
    }
}
