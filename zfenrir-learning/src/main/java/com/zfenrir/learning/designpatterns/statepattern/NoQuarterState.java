package com.zfenrir.learning.designpatterns.statepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NoQuarterState implements State{

    private Logger logger = LoggerFactory.getLogger(getClass());
    private GumballMachineV2 gumballMachineV2;
    
    public NoQuarterState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }
    @Override
    public void insertQuarter() {
       logger.info("You inserted a querter");
       gumballMachineV2.setState(gumballMachineV2.getHasQuarterState());
        
    }

    @Override
    public void ejectQuarter() {
        logger.info("you have not inser a quarter");
        
    }

    @Override
    public void turnCrank() {
        logger.info("you turned, but there is no quarter");
    }

    @Override
    public void dispense() {
        logger.info("you need to play first");        
    }

}
