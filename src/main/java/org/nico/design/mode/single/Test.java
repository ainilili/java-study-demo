package org.nico.design.mode.single;

public class Test {

	public static void main(String[] args) {
		
		Single single = Single.getInstance();
		
		System.out.println(single.addAndGet());
		
		single = Single.getInstance();
		
		System.out.println(single.addAndGet());
	}
}
