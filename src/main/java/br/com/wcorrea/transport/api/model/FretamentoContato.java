package br.com.wcorrea.transport.api.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Data
public class FretamentoContato implements Serializable {
    private static final long serialVersionUID = -6649170818493314243L;

    @Size(max = 150)
    private String nome;

    @Size(max = 20)
    private String telefone1;

    @Size(max = 20)
    private String telefone2;

    @Lob
    @Column(name = "obs")
    private String obs;

    public FretamentoContato() {
    }
}
