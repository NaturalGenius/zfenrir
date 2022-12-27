package com.zfenrir.learning.designpatterns.statepattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 状态模式 对应Head First 第388也
 * @author zhuliang
 *
 * 2022-11-22
 */
public class GumballMachineV1 {

    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 售罄状态
     */
    final static int SOLD_OUT = 0;
    /**
     * 未投币
     */
    final static int NO_QUARTER = 1;
    /**
     * 已投币
     */
    final static int HAS_QUARTER = 2;
    /**
     * 售出糖果
     */
    final static int SOLD = 3;
    //初始状态 售罄
    int state = SOLD_OUT;
    //库存量
    int count = 0;
    
    public GumballMachineV1(int count) {
        this.count = count;
        if (count > 0) {
            state = NO_QUARTER;
        }
    }
    /**
     * 投币
     */
    public void insertQuarter() {
        switch (state) {
            case HAS_QUARTER:
                logger.info("You can not insert another quarter");
                break;
            case SOLD_OUT:
                logger.info("You can not insert a quarter, the machine is sold out");
                break;
            case SOLD:
                logger.info("Please wait, we are already giving you a gumball");
                break;
            case NO_QUARTER:
                state = HAS_QUARTER;
                logger.info("You insert a quarter");
                break;
            default:
                break;
        }
    }
    
    /**
     * 退币
     */
    public void ejectQuarter() {
        switch (state) {
            case HAS_QUARTER:
                logger.info("Quarter return");
                state = NO_QUARTER;
                break;
            case NO_QUARTER:
                logger.info("You have not insert a quarter");
                break;
            case SOLD_OUT:
                logger.info("you con not eject You have not insert a quarter");
                break;
            case SOLD:
                logger.info("你已经拿到糖果了");
                break;
            default:
                break;
        }
    }
    /**
     * 获取糖果
     */
    public void turnCrank() {
        switch (state) {
            case SOLD:
                logger.info("你已经拿到糖果了");
                break;
            case NO_QUARTER:
                logger.info("请投币");
                break;
            case HAS_QUARTER:
                logger.info("请取回糖果");
                state = SOLD;
                dispense();
                break;
            case SOLD_OUT:
                logger.info("you turned but there are no gamballs");
                break;
            default:
                break;
        }
    }
    /**
     * 获取糖果
     */
    public void dispense() {
        switch (state) {
            case SOLD:
                logger.info("A gamball comes rolling out the slot");
                count--;
                if (count == 0) {
                    state = SOLD_OUT;
                }else {
                    state = NO_QUARTER;
                }
                break;
            case NO_QUARTER:
                logger.info("请投币");
                break;
            case HAS_QUARTER:
                logger.info("No gumball dispensed");
                break;
            case SOLD_OUT:
                logger.info("No gumball dispensed");
                break;
            default:
                break;
        }
    }
    @Override
    public String toString() {
        return "GumballMachine [state=" + state + ", count=" + count + "]";
    }
    
}
