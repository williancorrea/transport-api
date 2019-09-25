package br.com.wcorrea.transport.api.model.fretamento;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Lob;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Embeddable
@Data
public class FretamentoEventualContato implements Serializable {
    private static final long serialVersionUID = -3982075058435584113L;

    @Size(max = 150)
    private String nome;

    @Size(max = 20)
    private String telefone1;

    @Size(max = 20)
    private String telefone2;

    @Lob
    @Column(name = "obs")
    private String obs;

    public FretamentoEventualContato() {
    }
}
