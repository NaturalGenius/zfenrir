package com.zfenrir.learning.designpatterns.statepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 状态模式 对应Head First 第388也
 * @author zhuliang
 *
 * 2022-11-22
 */
public class GumballMachineV2 {

    private State soldOutState ;
    private State soldState ;
    private State hasQuarterState ;
    private State noQuarterState;
    private State winnerState;
    //库存量
    private int count = 0;
    //糖果机所在位置
    private String location;
    private State state = soldOutState;
    public GumballMachineV2(String location, int count) {
        this.noQuarterState = new NoQuarterState(this);
        this.hasQuarterState = new HasQuarterState(this);
        this.soldOutState = new SoldOutState(this);
        this.soldState = new SoldState(this);
        this.winnerState = new WinnerState(this);
        this.count = count;
        this.location = location;
        if (count > 0) {
            state = noQuarterState;
        }
    }
   
    public void insertQuarter() {
        state.insertQuarter();
    }

    public void ejectQuarter() {
        state.ejectQuarter();
    }

    public void turnCrank() {
        state.turnCrank();
        state.dispense();
    }

    void releaseBall() {
        if (count != 0) {
            count--;
        }
        
    }
    public State getSoldOutState() {
        return soldOutState;
    }

    public void setSoldOutState(State soldOutState) {
        this.soldOutState = soldOutState;
    }


    public State getSoldState() {
        return soldState;
    }





    public void setSoldState(State soldState) {
        this.soldState = soldState;
    }





    public State getHasQuarterState() {
        return hasQuarterState;
    }





    public void setHasQuarterState(State hasQuarterState) {
        this.hasQuarterState = hasQuarterState;
    }





    public State getNoQuarterState() {
        return noQuarterState;
    }





    public void setNoQuarterState(State noQuarterState) {
        this.noQuarterState = noQuarterState;
    }

    public State getWinnerState() {
        return winnerState;
    }

    public void setWinnerState(State winnerState) {
        this.winnerState = winnerState;
    }

    public State getState() {
        return state;
    }





    public void setState(State state) {
        this.state = state;
    }





    public int getCount() {
        return count;
    }


    public void setCount(int count) {
        this.count = count;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "GumballMachineV2 [count=" + count + ", location=" + location + ", state=" + state + "]";
    }

}
