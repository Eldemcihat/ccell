package com.ocs.DGW;

import java.util.Random;

public class OpNumberGenerator {
    enum InternetOPs {
        YOUTUBE,
        FACEBOOK,
        INSTAGRAM,
        GOOGLE,
        TIKTOK,
        SPOTIFY,
        SNAPCHAT,
        OTHER
    }

    enum VoiceOPs {
        TURKCELL,
        VODAFONE,
        TURK_TELEKOM,
        OTHER
    }

    enum SmsOPs {
        TURK_TELEKOM,
        VODAFONE,
        TURKCELL,
        OTHER
    }

    Random rand;

    OpNumberGenerator() {
        rand = new Random();
    }

    public String getOPNumber(String service, String msisdn) {
        if (service.equals("DATA")) {
            int randnum = rand.nextInt(InternetOPs.values().length + 1);
            InternetOPs internetOP;
            switch (randnum) {
                case 0:
                    internetOP = InternetOPs.YOUTUBE;
                    break;
                case 1:
                    internetOP = InternetOPs.FACEBOOK;
                    break;
                case 2:
                    internetOP = InternetOPs.INSTAGRAM;
                    break;
                case 3:
                    internetOP = InternetOPs.GOOGLE;
                    break;
                case 4:
                    internetOP = InternetOPs.SPOTIFY;
                    break;
                case 5:
                    internetOP = InternetOPs.TIKTOK;
                    break;
                case 6:
                    internetOP = InternetOPs.SNAPCHAT;
                    break;
                default:
                    internetOP = InternetOPs.OTHER;
                    break;
            }
            return internetOP.toString();
        } else if (service.equals("VOICE")) {
            int randnum = rand.nextInt(VoiceOPs.values().length + 1);
            VoiceOPs voiceOP;
            switch (randnum) {
                case 0:
                    voiceOP = VoiceOPs.TURKCELL;
                    break;
                case 1:
                    voiceOP = VoiceOPs.VODAFONE;
                    break;
                case 2:
                    voiceOP = VoiceOPs.TURK_TELEKOM;
                    break;
                default:
                    voiceOP = VoiceOPs.OTHER;
                    break;
            }
            return voiceOP.toString();
        } else {
            int randnum = rand.nextInt(SmsOPs.values().length + 1);
            SmsOPs smsOP;
            switch (randnum) {
                case 0:
                    smsOP = SmsOPs.TURK_TELEKOM;
                    break;
                case 1:
                    smsOP = SmsOPs.VODAFONE;
                    break;
                case 2:
                    smsOP = SmsOPs.TURKCELL;
                    break;
                default:
                    smsOP = SmsOPs.OTHER;
                    break;
            }
            return smsOP.toString();
        }
    }
}