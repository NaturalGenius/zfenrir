package com.zfenrir.learning.designpatterns.statepattern;

public enum GumballMachineStatus {
    
   SOLD_OUT(0, "售罄"), NO_QUARTER(1, "未投币"),HAS_QUARTER(2, "已投币"), SOLD(0, "售出");
    
    private int value;
    private String desc;
    
    private GumballMachineStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
}
