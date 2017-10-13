package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.CommonProperties;
import br.com.wcorrea.transport.api.utils.Cryptography;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "bank")
public class Bank implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Embedded
    private CommonProperties properties;

    @Size(max = 10)
    private String code;

    @NotNull
    @Size(min = 5, max = 150)
    private String name;

    @Size(min = 5, max = 250)
    private String url;

    public Bank() {
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Transient
    public String getKey() throws Exception {
        return this.id != null ? new Cryptography().encryptToHex(this.id.toString()) : null;
    }

    public CommonProperties getProperties() {
        return properties;
    }

    public void setProperties(CommonProperties properties) {
        this.properties = properties;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @PrePersist
    public void prePersist() {
        this.properties = new CommonProperties();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof Bank)) return false;

        Bank bank = (Bank) o;

        return new EqualsBuilder()
                .append(getId(), bank.getId())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getId())
                .toHashCode();
    }
}
