package org.nico.design.mode.single;

public class Single {
	
	private volatile static Single instance = null;
	
	private volatile int num = 0;
	
	private Single() {}
	
	public static Single getInstance() {
		if(instance == null) { 
			synchronized (Single.class) { 
				if(instance == null) { 
					instance = new Single(); 
				}
			}
		}
		return instance;
	}
	
	public int addAndGet() {
		return ++ num;
	}
	
}
