package org.nico.design.mode.test;

import org.junit.Test;
import org.nico.design.mode.single.Single;

public class SingleTest {

    @Test
    public void test() {
		
		Single single = Single.getInstance();
		
		System.out.println(single.addAndGet());
		
		single = Single.getInstance();
		
		System.out.println(single.addAndGet());
	}
}
