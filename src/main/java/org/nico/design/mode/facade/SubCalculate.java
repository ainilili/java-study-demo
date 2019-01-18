package org.nico.design.mode.facade;

public class SubCalculate implements Calculate{

    @Override
    public int cal(int a, int b) {
        return a - b;
    }

}
