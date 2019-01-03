package org.nico.design.model.bridge;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class WorkA implements Work{

	@Override
	public void process(int speed) {
		System.out.println("Work-A is producing by speed " + speed + "/s");		
	}

}
