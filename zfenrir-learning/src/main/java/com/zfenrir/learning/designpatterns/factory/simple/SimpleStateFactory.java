package com.zfenrir.learning.designpatterns.factory.simple;

import com.zfenrir.learning.designpatterns.statepattern.GumballMachineV2;
import com.zfenrir.learning.designpatterns.statepattern.HasQuarterState;
import com.zfenrir.learning.designpatterns.statepattern.NoQuarterState;
import com.zfenrir.learning.designpatterns.statepattern.SoldOutState;
import com.zfenrir.learning.designpatterns.statepattern.SoldState;
import com.zfenrir.learning.designpatterns.statepattern.State;

import java.util.Objects;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

/**
 * 简单工厂模式
 * @author zhuliang
 *
 * 2022-11-23
 */
public class SimpleStateFactory {

    public State createState(String stateName, GumballMachineV2 gumballMachineV2) {
        if (Objects.equals(stateName, NoQuarterState.class.getSimpleName())) {
            return new NoQuarterState(gumballMachineV2);
        } else if (Objects.equals(stateName, HasQuarterState.class.getSimpleName())) {
            return new HasQuarterState(gumballMachineV2);
        } else if (Objects.equals(stateName, SoldState.class.getSimpleName())) {
            return new SoldState(gumballMachineV2);
        }
        return new SoldOutState(gumballMachineV2);
    }
    
}
