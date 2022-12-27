package com.zfenrir.learning.designpatterns.proxy;

import com.zfenrir.learning.designpatterns.statepattern.GumballMachineV2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 糖果机监控器
 * @author zhuliang
 *
 * 2022-11-22
 */
public class GumballMonitor {

    private Logger logger = LoggerFactory.getLogger(getClass());
    private GumballMachineV2 gumballMachineV2;
    
    public GumballMonitor(GumballMachineV2 gumballMachineV2) {
        this.gumballMachineV2 = gumballMachineV2;
    }
    
    public void report() {
        logger.info("GumballMachine :{}", gumballMachineV2);
    }
}
