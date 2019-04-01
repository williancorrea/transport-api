package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.IdentificadorComum;
import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
import br.com.wcorrea.transport.api.service.exception.NivelEducacaoNaoEncontrado;
import br.com.wcorrea.transport.api.utils.Criptografia;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = true)
@Entity(name = "nivel_formacao")
@Data
public class NivelFormacao extends IdentificadorComum implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    @Size(min = 5, max = 150)
    private String nome;

    @Size(max = 512)
    @Lob
    private String descricao;

    @Column(name = "grau_de_instrucao_caged")
    private Long grauInstrucaoCaged;

    @Column(name = "grau_de_instrucao_sefip")
    private Long grauInstrucaoSefip;

    @Column(name = "grau_de_instrucao_rais")
    private Long grauInstrucaoRais;

    public NivelFormacao() {
    }
}
