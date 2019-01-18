package org.nico.design.mode.test;

import org.junit.Test;
import org.nico.design.mode.facade.CalculateFacade;

public class FacadeTest {

    @Test
    public void test() {
        CalculateFacade cf = new CalculateFacade();

        System.out.println(cf.add(1, 1));
        System.out.println(cf.sub(2, 1));
    }
}
