package org.nico.design.mode.facade;

public class AddCalculate implements Calculate{

    @Override
    public int cal(int a, int b) {
        return a + b;
    }

}
