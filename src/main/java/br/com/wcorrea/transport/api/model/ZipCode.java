package br.com.wcorrea.transport.api.model;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "zip_code")
public class ZipCode implements Serializable {

    @Id
    @NotNull
    @Size(min = 9, max = 9)
    @Column(name = "zip_code")
    private String zipCode;
    @Size(max = 250)
    private String address;
    @Size(max = 150)
    private String complement;
    @Size(max = 250)
    private String neighborhood;
    @Size(max = 250)
    private String locality;
    @NotNull
    @Size(min = 2, max = 2)
    private String uf;
    @Size(max = 250)
    private String unit;
    private Integer ibge;
    @Size(max = 250)
    private String gia;

    public ZipCode() {
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getIbge() {
        return ibge;
    }

    public void setIbge(Integer ibge) {
        this.ibge = ibge;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof ZipCode)) return false;

        ZipCode zipCode1 = (ZipCode) o;

        return new EqualsBuilder()
                .append(getZipCode(), zipCode1.getZipCode())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getZipCode())
                .toHashCode();
    }
}
