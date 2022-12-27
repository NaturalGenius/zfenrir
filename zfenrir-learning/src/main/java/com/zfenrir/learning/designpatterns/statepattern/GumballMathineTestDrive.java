package com.zfenrir.learning.designpatterns.statepattern;

public class GumballMathineTestDrive {

    public static void main(String[] args) {
        GumballMachineV1 gumballMachine = new GumballMachineV1(5);
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        gumballMachine.turnCrank();
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.ejectQuarter();
        System.out.println(gumballMachine);
        gumballMachine.insertQuarter();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.turnCrank();
        gumballMachine.insertQuarter();
        gumballMachine.ejectQuarter();
        System.out.println(gumballMachine);
        
        GumballMachineV2 gumballMachineV2 = new GumballMachineV2("北京", 5);
        gumballMachineV2.insertQuarter();
        gumballMachineV2.turnCrank();
        System.out.println(gumballMachineV2);
        gumballMachineV2.insertQuarter();
        gumballMachineV2.turnCrank();
        System.out.println(gumballMachineV2);
        gumballMachineV2.insertQuarter();
        gumballMachineV2.turnCrank();
        System.out.println(gumballMachineV2);
    }
}
