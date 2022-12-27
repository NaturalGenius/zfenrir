package com.zfenrir.learning.designpatterns.statepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoldState implements State{

    private Logger logger = LoggerFactory.getLogger(getClass());
    private GumballMachineV2 gumballMachineV2;
    
    public SoldState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }
    @Override
    public void insertQuarter() {
        logger.info("Please wait, we are already giving you a gumball");
    }

    @Override
    public void ejectQuarter() {
        logger.info("你已经拿到糖果了");
    }

    @Override
    public void turnCrank() {
        logger.info("你已经拿到糖果了");
    }

    @Override
    public void dispense() {
        gumballMachineV2.releaseBall();
        if (gumballMachineV2.getCount() <= 0) {
            gumballMachineV2.setState(gumballMachineV2.getSoldOutState());
        }else {
            gumballMachineV2.setState(gumballMachineV2.getNoQuarterState());
        }
        
    }

}
