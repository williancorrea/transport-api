package br.com.wcorrea.transport.api.model;

import br.com.wcorrea.transport.api.model.common.CreationDateTime;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
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
    @Size(max = 250)
    private String neighborhood;
    @Size(max = 250)
    private String city;
    @NotNull
    @Size(min = 2, max = 2)
    private String uf;
    @Size(max = 20)
    private String ibge;
    private Integer ddd;
    @Size(max = 30)
    private String longitude;
    @Size(max = 30)
    private String latitude;
    private Integer altitude;

    @Embedded
    private CreationDateTime properties;

    public ZipCode() {
        this.properties = new CreationDateTime();
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

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }


    public String getIbge() {
        return ibge;
    }

    public void setIbge(String ibge) {
        this.ibge = ibge;
    }

    public Integer getDdd() {
        return ddd;
    }

    public void setDdd(Integer ddd) {
        this.ddd = ddd;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public Integer getAltitude() {
        return altitude;
    }

    public void setAltitude(Integer altitude) {
        this.altitude = altitude;
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
