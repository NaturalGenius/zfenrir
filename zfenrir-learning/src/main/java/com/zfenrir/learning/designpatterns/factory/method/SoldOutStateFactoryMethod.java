package com.zfenrir.learning.designpatterns.factory.method;

import com.zfenrir.learning.designpatterns.statepattern.SoldOutState;
import com.zfenrir.learning.designpatterns.statepattern.State;

public class SoldOutStateFactoryMethod extends AbstractStateFactoryMethod{

    @Override
    public State creaState() {
        return new SoldOutState(null);
    }

}
