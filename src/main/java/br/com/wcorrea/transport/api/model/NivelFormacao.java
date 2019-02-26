package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.NivelEducacaoNaoEncontrado;
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
import java.util.Date;

@ToString
@EqualsAndHashCode
@Entity(name = "nivel_formacao")
public class NivelFormacao extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String nome;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String descricao;

    @Column(name = "grau_de_instrucao_caged")
    @Getter
    @Setter
    private Long grauInstrucaoCaged;

    @Column(name = "grau_de_instrucao_sefip")
    @Getter
    @Setter
    private Long grauInstrucaoSefip;

    @Column(name = "grau_de_instrucao_rais")
    @Getter
    @Setter
    private Long grauInstrucaoRais;

    public NivelFormacao() {
    }
}
