package com.ocs.ccell.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PackageBalanceRemaining {

    @SerializedName("BalanceData")
    @Expose
    int data;
    @SerializedName("BalanceSms")
    @Expose
    int sms;
    @SerializedName("BalanceVoice")
    @Expose
    int voice;
    @SerializedName("Price")
    @Expose
    int price;
    @SerializedName("pack")
    @Expose
    Pack pack;

    public PackageBalanceRemaining(int data,int voice,int sms,int price) {
        this.data = data;
        this.voice = voice;
        this.sms = sms;
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSms() {
        return sms;
    }

    public void setSms(int sms) {
        this.sms = sms;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public Pack getPack() { return pack; }

    public void setPack(Pack pack) { this.pack = pack; }
    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }
}
