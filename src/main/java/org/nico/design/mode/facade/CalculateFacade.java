package org.nico.design.mode.facade;

public class CalculateFacade {

    private Calculate addCalculate;
    
    private Calculate subCalculate;
    
    public CalculateFacade() {
        this.addCalculate = new AddCalculate();
        this.subCalculate = new SubCalculate();
    }
    
    public int add(int a, int b) {
        return this.addCalculate.cal(a, b);
    }
    
    public int sub(int a, int b) {
        return this.subCalculate.cal(a, b);
    }
}
