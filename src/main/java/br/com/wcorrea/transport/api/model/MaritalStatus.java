package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.CommonProperties;
import br.com.wcorrea.transport.api.utils.Cryptography;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@ToString
@EqualsAndHashCode
@Entity(name = "marital_status")
public class MaritalStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @JsonIgnore
    @Getter
    @Setter
    private Long id;

    @Embedded
    @Getter
    @Setter
    private CommonProperties properties;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String name;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    @Column(name = "descricao")
    private String description;

    public MaritalStatus() {
    }

    @Transient
    public String getKey() throws Exception {
        return this.id != null ? new Cryptography().encryptToHex(this.id.toString()) : null;
    }

    @Transient
    public void setKey(String key) throws Exception {
        this.id = Long.parseLong(new Cryptography().decryptFromHex(key));
    }

    @PrePersist
    public void prePersist() {
        this.properties = new CommonProperties();
    }

    @PreUpdate
    public void preUpdade() {
        this.properties.setModificationDate(LocalDateTime.now());
    }
}
