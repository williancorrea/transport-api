package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.PropriedadesComuns;
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
@Entity(name = "type_of_relationship")
public class TypeRelationship implements Serializable {
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
    private PropriedadesComuns properties;

    @NotNull
    @Size(min = 1, max = 3)
    @Getter
    @Setter
    private String code;

    @NotNull
    @Size(min = 5, max = 150)
    @Getter
    @Setter
    private String name;

    @Size(max = 512)
    @Lob
    @Getter
    @Setter
    private String description;

    public TypeRelationship() {
    }

    @Transient
    public String getKey() throws Exception {
        return this.id != null ? new Cryptography().encryptToHex(this.id.toString()) : null;
    }

    @Transient
    public void setKey(String key) throws Exception {
        if (id != null) {
            this.id = Long.parseLong(new Cryptography().decryptFromHex(key));
        }
    }

    @PrePersist
    public void prePersist() {
        this.properties = new PropriedadesComuns();
    }
}
