package com.ocs.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Package")
public class Package {

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
    @OneToMany(mappedBy = "pack", cascade = CascadeType.ALL)
    private List<Subscriber> subscribers = new ArrayList<>();

    public Package() {

    }

    public Package(String name, int voice, int sms, int data, int duration) {
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
