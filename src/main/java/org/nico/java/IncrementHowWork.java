package org.nico.java;

public class IncrementHowWork {

    public void before() {
        int i = 1;
        i = ++ i;
        System.out.println(i);
    }
    
    public void after() {
        int i = 1;
        i = i ++;
        System.out.println(i);
    }
    
    public static void main(String[] args) {
        IncrementHowWork ihw = new IncrementHowWork();
        ihw.before();
        ihw.after();
    }
}
