package com.ocs.apiv1.Controller;

import java.util.Objects;

public class PackageResponse {
    private Integer id;
    private String name;

    public PackageResponse() {
    }

    public PackageResponse(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PackageResponse id(Integer id) {
        setId(id);
        return this;
    }

    public PackageResponse name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof PackageResponse)) {
            return false;
        }
        PackageResponse packageResponse = (PackageResponse) o;
        return Objects.equals(id, packageResponse.id) && Objects.equals(name, packageResponse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{" +
                " id='" + getId() + "'" +
                ", name='" + getName() + "'" +
                "}";
    }

    // Getters and setters

    // Constructors
}
