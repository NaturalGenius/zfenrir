package com.zfenrir.learning.designpatterns.statepattern;

public interface State {
   /**
    * 投币
    */
   void insertQuarter();
   /**
    * 退币
    */
   void ejectQuarter();
   /**
    * 获取糖果
    */
   void turnCrank();
   /**
    * 发放糖果
    */
   void dispense();
}
