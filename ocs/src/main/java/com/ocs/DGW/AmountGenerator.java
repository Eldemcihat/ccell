package com.ocs.DGW;

import java.util.Random;

public class AmountGenerator {

    Random rand;
    final int voiceBound = 60;
    final int smsBound = 1;
    final int dataBound = 300;

    // voice: minute
    // sms: 1
    // data megabit
    AmountGenerator() {
        rand = new Random();
    }

    public int getAmount(String service) {
        switch (service) {
            case "VOICE":
                return rand.nextInt(voiceBound);
            case "DATA":
                return rand.nextInt(dataBound);
            case "SMS":
                return smsBound;
            default:
                return -1;
        }
    }
}