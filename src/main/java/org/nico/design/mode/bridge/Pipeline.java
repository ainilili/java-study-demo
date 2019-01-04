package org.nico.design.mode.bridge;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public abstract class Pipeline {

	protected int speed;
	
	protected Work work;

	public Pipeline(int speed, Work work) {
		super();
		this.speed = speed;
		this.work = work;
	}
	
	/**
	 * 生产线动作抽象方法
	 */
	public abstract void start();
	
}
