package br.com.wcorrea.transport.api.model.pessoa;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@ToString(exclude = {"pessoaFisica"})
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "pessoa")
@Data
public class Pessoa extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotBlank
    @Size(min = 5, max = 250)
    private String nome;

    @Size(max = 250)
    private String fantasia;

    @Lob
    @Column(name = "imagem_base_64")
    private String imagem;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PessoaTipo tipo;

    @Size(max = 250)
    @Email
    private String email;

    @Size(max = 250)
    private String site;

    @Size(max = 20)
    private String telefone1;

    @Size(max = 100)
    private String telefone1Obs;

    @Size(max = 20)
    private String telefone2;

    @Size(max = 100)
    private String telefone2Obs;

    @Size(max = 250)
    private String endereco;

    @Size(max = 100)
    private String bairro;

    @Size(max = 9)
    private String cep;

    @Lob
    @Column(name = "obs")
    private String obs;

    private boolean inativo;

    @JsonProperty("pessoaFisica")
    @JsonIgnoreProperties({"pessoa", "controle", "orgaoRg", "dataEmissaoRg", "dataNascimento", "naturalidade", "nacionalidade", "tipoSangue", "cnhNumero", "cnhCategoria", "cnhVencimento", "tituloEleitoralNumero", "tituloEleitoralZona", "tituloEleitoralSecao", "reservistaNumero", "reservistaCategoria", "nomeMae", "nomePai", "estadoCivil"})
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaFisica pessoaFisica;

    @JsonProperty("pessoaJuridica")
    @JsonIgnoreProperties("pessoa")
    @OneToOne(fetch = FetchType.EAGER, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private PessoaJuridica pessoaJuridica;

    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    @ManyToOne()
    private Cidade cidade;







    @JsonProperty("listaPessoaEndereco")
    @JsonIgnoreProperties("pessoa")
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaEndereco> listaPessoaEndereco;

    @JsonProperty("listaPessoaTelefone")
    @JsonIgnoreProperties("pessoa")
    @Valid
    @OneToMany(mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaTelefone> listaPessoaTelefone;


    @JsonProperty("listaPessoaContato")
    @JsonIgnoreProperties("pessoa")
    @Valid
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pessoa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PessoaContato> listaPessoaContato;

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
