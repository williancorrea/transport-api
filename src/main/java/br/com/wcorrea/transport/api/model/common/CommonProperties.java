package br.com.wcorrea.transport.api.model.common;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
public class CommonProperties implements Serializable {

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "modification_date")
    private LocalDateTime modificationDate;

    public CommonProperties() {
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
