package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.CommonProperties;
import br.com.wcorrea.transport.api.utils.Cryptography;
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
@Entity(name = "person")
public class Person {
    private static final long serialVersionUID = -1537213210424146788L;

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
    private CommonProperties properties;

    @NotBlank
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String name;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private PersonType type;

    @Size(min = 5, max = 250)
    @Getter
    @Setter
    private String email;

    @Size(min = 5, max = 250)
    @Getter
    @Setter
    private String site;

    @Getter
    @Setter
    private boolean client;

    @Getter
    @Setter
    private boolean student;

    @Getter
    @Setter
    private boolean provider;

    @Getter
    @Setter
    private boolean collaborator;

    @Getter
    @Setter
    private boolean shippingCompany;

    @Getter
    @Setter
    private boolean removed;

//    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL)
//    private PessoaFisica pessoaFisica;
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

    public Person() {
    }

    @JsonIgnore
    @Transient
    public boolean isPhysicalPerson() {
        return PersonType.PHYSICAL.equals(type);
    }

    @Transient
    public String getKey() throws Exception {
        return this.id != null ? new Cryptography().encryptToHex(this.id.toString()) : null;
    }

    @PrePersist
    public void prePersist() {
        this.properties = new CommonProperties();
    }
}
