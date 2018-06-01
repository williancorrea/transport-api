package br.com.wcorrea.transport.api.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class PropriedadesComuns implements Serializable {
    private static final long serialVersionUID = 4575371763430219942L;

    @Column(name = "data_criacao")
    private LocalDateTime dateCreation;

    @Column(name = "data_alteracao")
    private LocalDateTime modificationDate;

    public PropriedadesComuns() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.modificationDate = dateTime;
        this.dateCreation = dateTime;
    }

    public String getDateCreation() {
        return dateCreation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getModificationDate() {
        return modificationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setModificationDate(LocalDateTime modificationDate) {
        this.modificationDate = LocalDateTime.now();
    }
}
