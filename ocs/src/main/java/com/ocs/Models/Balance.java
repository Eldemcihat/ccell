package com.ocs.Models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PackageId")
    private Package pack;

    @ManyToOne
    @JoinColumn(name = "SubscriberId")
    private Subscriber subscriberId;

    @Column(name = "BalanceVoice")
    private int balanceVoice;
    @Column(name = "BalanceSms")
    private int balanceSms;
    @Column(name = "BalanceData")
    private int balanceData;

    public Balance() {
    }

    public Balance(Long id, Package pack, Subscriber subscriberId, int balanceVoice, int balanceSms,
            int balanceData) {
        this.id = id;
        this.pack = pack;
        this.subscriberId = subscriberId;
        this.balanceVoice = balanceVoice;
        this.balanceSms = balanceSms;
        this.balanceData = balanceData;

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Package getPack() {
        return this.pack;
    }

    public void setPack(Package pack) {
        this.pack = pack;
    }

    public Subscriber getSubscriberId() {
        return this.subscriberId;
    }

    public void setSubscriberId(Subscriber subscriberId) {
        this.subscriberId = subscriberId;
    }

    public int getBalanceVoice() {
        return this.balanceVoice;
    }

    public void setBalanceVoice(int balanceVoice) {
        this.balanceVoice = balanceVoice;
    }

    public int getBalanceSms() {
        return this.balanceSms;
    }

    public void setBalanceSms(int balanceSms) {
        this.balanceSms = balanceSms;
    }

    public int getBalanceData() {
        return this.balanceData;
    }

    public void setBalanceData(int balanceData) {
        this.balanceData = balanceData;
    }

    public Balance id(Long id) {
        setId(id);
        return this;
    }

    public Balance pack(Package pack) {
        setPack(pack);
        return this;
    }

    public Balance subscriber(Subscriber subscriberId) {
        setSubscriberId(subscriberId);
        return this;
    }

    public Balance balanceVoice(int balanceVoice) {
        setBalanceVoice(balanceVoice);
        return this;
    }

    public Balance balanceSms(int balanceSms) {
        setBalanceSms(balanceSms);
        return this;
    }

    public Balance balanceData(int balanceData) {
        setBalanceData(balanceData);
        return this;
    }

    public String toString() {
        return "Balance [id=" + id + ", package=" + pack + ", voice=" + balanceVoice + ", data=" + balanceData
                + ", sms=" + balanceSms + "]";
    }
}
