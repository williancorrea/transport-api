package br.com.wcorrea.transport.api.model;

import java.io.Serializable;

/**
 * Class responsible for managing all the attributes of a city
 * Created by wcorrea on 19/08/17.
 */
public class City implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private Integer ibgeCode;
    private State state;

    public City() {
    }

    public City(long id, String name, Integer ibgeCode, State state) {
        this.id = id;
        this.name = name;
        this.ibgeCode = ibgeCode;
        this.state = state;
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

    public Integer getIbgeCode() {
        return ibgeCode;
    }

    public void setIbgeCode(Integer ibgeCode) {
        this.ibgeCode = ibgeCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof City)) return false;

        City city = (City) o;

        return getId() == city.getId();
    }

    @Override
    public int hashCode() {
        return (int) (getId() ^ (getId() >>> 32));
    }
}
