package br.com.wcorrea.transport.api.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Class responsible for managing all attributes of a state
 * Created by Willian Vagner Vicente Corrêa (willian.vag@gmail.com) on 19/08/17.
 */
@Entity(name = "state")
public class State {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "state_id")
    private long id;

    @NotNull
    @Size(min = 3, max = 150)
    private String name;

    @NotNull
    @Size(min = 2, max = 2)
    @Column(name = "initials")
    private String initials;

    @Column(name = "ibge_code")
    private Integer ibgeCode;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    private Country country;

    public State() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public Integer getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(Integer ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof State)) return false;

        State state = (State) o;

        return getId() == state.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
