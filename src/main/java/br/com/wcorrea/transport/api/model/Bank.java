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

@ToString
@EqualsAndHashCode
@Entity(name = "banks")
public class Bank implements Serializable {

    private static final long serialVersionUID = -1537213210424146788L;
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

    @Size(max = 10)
    @Getter
    @Setter
    private String code;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String name;

    @Size(min = 5, max = 250)
    @Getter
    @Setter
    private String url;

    public Bank() {
    }

    @Transient
    public String getKey() throws Exception {
        return this.id != null ? new Cryptography().encryptToHex(this.id.toString()) : null;
    }

    @PrePersist
    public void prePersist() {
        this.properties = new CommonProperties();
    }
}
