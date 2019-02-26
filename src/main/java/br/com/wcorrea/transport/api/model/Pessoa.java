package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.PessoaNaoEncontrada;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@ToString(exclude = {"pessoaFisica"})
@EqualsAndHashCode(exclude = {"pessoaFisica"})
@Entity(name = "pessoa")
public class Pessoa extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    @NotBlank
    @Size(min = 5, max = 250)
    private String nome;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private PessoaTipo tipo;

    @Size(max = 250)
    @Getter
    @Setter
    @Email
    private String email;

    @Size(max = 250)
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
    @JsonProperty("pessoaFisica")
    @JsonIgnoreProperties("pessoa")
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaFisica pessoaFisica;

    @Getter
    @Setter
    @JsonProperty("pessoaJuridica")
    @JsonIgnoreProperties("pessoa")
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaJuridica pessoaJuridica;

    @Getter
    @Setter
    @JsonProperty("listaPessoaEndereco")
    @JsonIgnoreProperties("pessoa")
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaEndereco> listaPessoaEndereco;

    @Getter
    @Setter
    @JsonProperty("listaPessoaTelefone")
    @JsonIgnoreProperties("pessoa")
    @Valid
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaTelefone> listaPessoaTelefone;


    @Getter
    @Setter
    @JsonProperty("listaPessoaContato")
    @JsonIgnoreProperties("pessoa")
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaContato> listaPessoaContato;

    @Getter
    @Setter
    @JsonProperty("listaPessoaAuditoria")
    @JsonIgnoreProperties("pessoa")
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.MERGE)
    private List<PessoaAuditoria> listaPessoaAuditoria;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(nome = "EMPRESA_PESSOA", joinColumns = { @JoinColumn(nome = "ID_PESSOA") }, inverseJoinColumns = { @JoinColumn(nome = "ID_EMPRESA") })
//    private Set<Empresa> listaEmpresa;

    public Pessoa() {
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
}
