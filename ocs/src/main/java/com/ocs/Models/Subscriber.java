package com.ocs.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "subscriber")
public class Subscriber {

    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Surname")
    private String surname;

    @Column(name = "Email")
    private String email;
    @Column(name = "Msisdn")
    private String msisdn;
    @Column(name = "Password")
    private String password;
    @ManyToOne
    @JoinColumn(name = "PackageId")
    private Package pack;

    public Subscriber() {

    }

    public Subscriber(String name, String surname, String email, String msisdn, String password) {
        super();
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.msisdn = msisdn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Subscriber [id=" + id + ", firstName=" + name + ", lastName=" + surname + ", email=" + email
                + ",msisdn=" + msisdn + ",password=" + password + "]";
    }

}
