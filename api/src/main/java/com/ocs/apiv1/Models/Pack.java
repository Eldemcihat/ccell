package com.ocs.apiv1.Models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Package")
public class Pack {

    @Id
    @Column(name = "Id")
    private int id;

    @Column(name = "Name")
    private String name;

    @Column(name = "Voice")
    private int voice;

    @Column(name = "Sms")
    private int sms;
    @Column(name = "Data")
    private int data;
    @Column(name = "Duration")
    private int duration;
    @OneToMany(mappedBy = "pack")
    private List<Subscriber> subscribers;
    @OneToMany(mappedBy = "pack")
    private List<Balance> balances;

    public Pack() {

    }

    public Pack(String name, int voice, int sms, int data, int duration) {
        super();
        this.name = name;
        this.voice = voice;
        this.data = data;
        this.sms = sms;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public int getSms() {
        return this.sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
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

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Package [id=" + id + ", name=" + name + ", voice=" + voice + ", data=" + data + ",sms=" + sms
                + ",duration=" + duration + "]";
    }
}
