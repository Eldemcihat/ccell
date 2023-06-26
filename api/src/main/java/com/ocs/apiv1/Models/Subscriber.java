package com.ocs.apiv1.Models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Subscriber")
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
    private Pack pack;

    @OneToMany(mappedBy = "subscriber", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Balance> balances;

    public Subscriber() {
    }

    public Subscriber(String name, String surname, String email, String msisdn, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.msisdn = msisdn;
        this.password = password;
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

    public String getMsisdn() {
        return this.msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Pack getPack() {
        return this.pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Balance> getBalances() {
        return this.balances;
    }

    public void setBalances(List<Balance> balances) {
        this.balances = balances;
    }

    @Override
    public String toString() {
        return "Subscriber [id=" + id + ", firstName=" + name + ", lastName=" + surname + ", email=" + email
                + ",msisdn=" + msisdn + ",password=" + password + ",balance=" + balances + "]";
    }

}
