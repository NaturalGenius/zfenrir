package com.zfenrir.learning.designpatterns.factory.method;

import com.zfenrir.learning.designpatterns.statepattern.SoldState;
import com.zfenrir.learning.designpatterns.statepattern.State;

public class SoldStateFactoryMethod extends AbstractStateFactoryMethod{

    @Override
    public State creaState() {
        return new SoldState(null);
    }

}
