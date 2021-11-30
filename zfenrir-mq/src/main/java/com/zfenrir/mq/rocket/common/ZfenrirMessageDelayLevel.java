package com.zfenrir.mq.rocket.common;

public enum ZfenrirMessageDelayLevel {
    
    ONE_SEC(1,"1s"),
    FIVE_SEC(2,"5s"),
    TEN_SEC(3,"10s"),
    THIRTY_SEC(4,"30s"),
    ONE_MIN(5,"1m"),
    TWO_MIN(6,"2m"),
    THREE_MIN(7,"3m"),
    FOUR_MIN(8,"4m"),
    FIVE_MIN(9,"5m"),
    SIX_MIN(10,"6m"),
    SEVEN_MIN(11,"7m"),
    EIGHT_MIN(12,"8m"),
    NINE_MIN(13,"9m"),
    TEN_MIN(14,"10m"),
    TWENTY_MIN(15,"20m"),
    THIRTY_MIN(16,"30m"),
    ONE_HOUR(17,"1h"),
    TWO_HOUR(18,"2h");
    
    private int delayLevel;
    private String delayTimeDesc;
    
    private ZfenrirMessageDelayLevel(int delayLevel, String delayTimeDesc) {
        this.delayLevel = delayLevel;
        this.delayTimeDesc = delayTimeDesc;
    }

    public int getDelayLevel() {
        return delayLevel;
    }

    public String getDelayTimeDesc() {
        return delayTimeDesc;
    }
    
}
