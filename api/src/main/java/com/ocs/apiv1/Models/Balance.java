package com.ocs.apiv1.Models;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "balance")
public class Balance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;
    @Column(name = "BalanceVoice")
    @JsonProperty("BalanceVoice")
    private BigDecimal balanceVoice;
    @JsonProperty("BalanceSms")
    @Column(name = "BalanceSms")
    private BigDecimal balanceSms;
    @JsonProperty("BalanceData")
    @Column(name = "BalanceData")
    private BigDecimal balanceData;
    @JsonProperty("Price")

    @Column(name = "Price")
    private BigDecimal price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PackageId")
    private Pack pack;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SubscriberId")
    @JsonIgnore
    private Subscriber subscriber;

    public Balance() {
    }

    public Balance(Long id, Pack pack, Subscriber subscriber, BigDecimal balanceVoice, BigDecimal balanceSms,
            BigDecimal balanceData, BigDecimal price) {
        this.id = id;
        this.pack = pack;
        this.subscriber = subscriber;
        this.balanceVoice = balanceVoice;
        this.balanceSms = balanceSms;
        this.balanceData = balanceData;
        this.price = price;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pack getPack() {
        return this.pack;
    }

    public void setPack(Pack pack) {
        this.pack = pack;
    }

    public Subscriber getSubscriber() {
        return this.subscriber;
    }

    public void setSubscriber(Subscriber subscriber) {
        this.subscriber = subscriber;
    }

    public BigDecimal getBalanceVoice() {
        return this.balanceVoice;
    }

    public void setBalanceVoice(BigDecimal balanceVoice) {
        this.balanceVoice = balanceVoice;
    }

    public BigDecimal getBalanceSms() {
        return this.balanceSms;
    }

    public void setBalanceSms(BigDecimal balanceSms) {
        this.balanceSms = balanceSms;
    }

    public BigDecimal getBalanceData() {
        return this.balanceData;
    }

    public void setBalanceData(BigDecimal balanceData) {
        this.balanceData = balanceData;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Balance id(Long id) {
        setId(id);
        return this;
    }

    public Balance pack(Pack pack) {
        setPack(pack);
        return this;
    }

    public Balance subscriber(Subscriber subscriber) {
        setSubscriber(subscriber);
        return this;
    }

    public Balance balanceVoice(BigDecimal balanceVoice) {
        setBalanceVoice(balanceVoice);
        return this;
    }

    public Balance balanceSms(BigDecimal balanceSms) {
        setBalanceSms(balanceSms);
        return this;
    }

    public Balance balanceData(BigDecimal balanceData) {
        setBalanceData(balanceData);
        return this;
    }

    public Balance price(BigDecimal price) {
        setPrice(price);
        return this;
    }

    public String toString() {
        return "Balance [id=" + id + ", voice=" + balanceVoice + ", data=" + balanceData
                + ", sms=" + balanceSms + ", price=" + price + "]";
    }
}
