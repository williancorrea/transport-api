package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.CreationDateTime;
import br.com.wcorrea.transport.api.utils.Cryptography;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
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

    @Transient
    private String key;

    @Size(max = 10)
    private String code;

    @NotNull
    @Size(min = 5, max = 150)
    private String name;

    @Size(min = 5, max = 250)
    private String url;

    @Embedded
    private CreationDateTime properties;

    public Bank() {
    }

    @JsonIgnore
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() throws Exception {
        return this.id != null ? new Cryptography().encryptToHex(this.id.toString()) : null;
    }

    public void setKey(String key) throws Exception {
        this.key = StringUtils.isBlank(key) ? "" : new Cryptography().decryptFromHex(key);
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

    public CreationDateTime getProperties() {
        return properties;
    }

    public void setProperties(CreationDateTime properties) {
        this.properties = properties;
    }

    @PrePersist
    public void prePersist() {
        this.properties = new CreationDateTime();
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
