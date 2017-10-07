package br.com.wcorrea.transport.api.model.common;

import org.apache.tomcat.jni.Local;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Embeddable
@Access(AccessType.FIELD)
public class CreationDateTime {

    @Column(name = "date_creation")
    private LocalDateTime dateCreation;

    @Column(name = "last_update")
    private LocalDateTime lastUpdate;

    public CreationDateTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        this.lastUpdate = dateTime;
        this.dateCreation = dateTime;
    }

    public String getDateCreation() {
        return dateCreation.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public String getLastUpdate() {
        return lastUpdate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = LocalDateTime.now();
    }
}
