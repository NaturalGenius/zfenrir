package com.zfenrir.learning.designpatterns.statepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ThreadLocalRandom;

public class HasQuarterState implements State{
    private Logger logger = LoggerFactory.getLogger(getClass());
    private GumballMachineV2 gumballMachineV2;
    
    public HasQuarterState(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }
    @Override
    public void insertQuarter() {
        logger.info("You can not insert another quarter");
    }

    @Override
    public void ejectQuarter() {
        logger.info("Quarter return");
        gumballMachineV2.setState(gumballMachineV2.getNoQuarterState());;
    }

    @Override
    public void turnCrank() {
        logger.info("请取回糖果");
        int nextInt = ThreadLocalRandom.current().nextInt(10);
        if (nextInt == 0 && gumballMachineV2.getCount() > 1) {
            gumballMachineV2.setState(gumballMachineV2.getWinnerState());
        }else {
            gumballMachineV2.setState(gumballMachineV2.getSoldState());
        }
    }

    @Override
    public void dispense() {
        logger.info("No gumball dispensed");
    }

}
