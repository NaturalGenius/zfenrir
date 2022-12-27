package com.zfenrir.learning.designpatterns.statepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SoldOutState implements State{

    private Logger logger = LoggerFactory.getLogger(getClass());
    private GumballMachineV2 gumballMachineV2;
    
    public SoldOutState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }
    @Override
    public void insertQuarter() {
        logger.info("You can not insert a quarter, the machine is sold out");
    }

    @Override
    public void ejectQuarter() {
        logger.info("you con not eject, You have not insert a quarter");
    }

    @Override
    public void turnCrank() {
        logger.info("you turned but there are no gamballs");
    }

    @Override
    public void dispense() {
        logger.info("No gumball dispensed");
    }

}
