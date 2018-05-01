package br.com.wcorrea.transport.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity(name = "country")
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private Long country_id;

    @NotNull
    @Size(min = 3, max = 150)
    private String name;

    @NotNull
    @Size(min = 2, max = 2)
    private String initials2;

    @NotNull
    @Size(min = 3, max = 3)
    private String initials3;

    private Integer international_code;

    public Country() {
    }

    public Long getCountry_id() {
        return country_id;
    }

    public void setCountry_id(Long country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials2() {
        return initials2;
    }

    public void setInitials2(String initials2) {
        this.initials2 = initials2;
    }

    public String getInitials3() {
        return initials3;
    }

    public void setInitials3(String initials3) {
        this.initials3 = initials3;
    }

    public Integer getInternational_code() {
        return international_code;
    }

    public void setInternational_code(Integer international_code) {
        this.international_code = international_code;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Country other = (Country) obj;
        if (getCountry_id() == null) {
            if (other.getCountry_id() != null)
                return false;
        } else if (!getCountry_id().equals(other.getCountry_id()))
            return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCountry_id() == null) ? 0 : getCountry_id().hashCode());
        return result;
    }
}
