package br.com.wcorrea.transport.api.model.common;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class PropriedadesComuns implements Serializable {
    private static final long serialVersionUID = 4575371763430219942L;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    public PropriedadesComuns() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.dataAlteracao = dateTime;
        this.dataCriacao = dateTime;
    }

    public String getDataCriacao() {
        return dataCriacao.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getDataAlteracao() {
        return dataAlteracao.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setDataAlteracao(LocalDateTime dataAlteracao) {
        this.dataAlteracao = dataAlteracao;
    }
}
