package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

@ToString(exclude = {"pessoaFisica"})
@EqualsAndHashCode(exclude = {"pessoaFisica"})
@Entity(name = "pessoa")
public class Pessoa implements Serializable {
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
    private PropriedadesComuns propriedades;

    @NotBlank
    @Size(min = 5, max = 250)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private PessoaTipo tipo;

    @Size(min = 5, max = 250)
    @Getter
    @Setter
    @Email
    private String email;

    @Size(min = 5, max = 250)
    @Getter
    @Setter
    private String site;

    @Getter
    @Setter
    private boolean cliente;

    @Getter
    @Setter
    private boolean estudante;

    @Getter
    @Setter
    private boolean fornecedor;

    @Getter
    @Setter
    private boolean colaborador;

    @Getter
    @Setter
    private boolean transportadora;

    @Getter
    @Setter
    private boolean inativo;

    @Getter
    @Setter
    @JsonIgnoreProperties("pessoa")
    @JsonProperty("pessoaFisica")
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaFisica pessoaFisica;
//
//    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL)
//    private PessoaJuridica pessoaJuridica;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<PessoaEndereco> listaPessoaEndereco;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<PessoaContato> listaPessoaContato;
//
//    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Set<PessoaTelefone> listaPessoaTelefone;
//
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(nome = "EMPRESA_PESSOA", joinColumns = { @JoinColumn(nome = "ID_PESSOA") }, inverseJoinColumns = { @JoinColumn(nome = "ID_EMPRESA") })
//    private Set<Empresa> listaEmpresa;

    public Pessoa() {
    }

    public String getNome() {
        return this.nome != null ? this.nome.toUpperCase() : this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    @JsonIgnore
    @Transient
    public boolean isPessoaFisica() {
        return PessoaTipo.FISICA.equals(tipo);
    }

    @JsonIgnore
    @Transient
    public boolean isPessoaJuridica() {
        return PessoaTipo.JURIDICA.equals(tipo);
    }


    @Transient
    public String getKey() throws Exception {
        try {
            return this.id != null ? new Criptografia().encryptToHex(this.id.toString()) : null;
        } catch (Exception e) {
            return null;
        }
    }

    @Transient
    public void setKey(String key) throws Exception {
        this.id = Long.parseLong(new Criptografia().decryptFromHex(key));
    }

    @PrePersist
    public void prePersist() {
        this.propriedades = new PropriedadesComuns();
    }

    @PreUpdate
    public void preUpdate() {
        this.propriedades.setDataAlteracao(new Date());
    }
}
