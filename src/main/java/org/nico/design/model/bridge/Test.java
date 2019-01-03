package org.nico.design.model.bridge;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class Test {

	public static void main(String[] args) {
		
		Pipeline pl2 = new PipelineA(2, new WorkA());
		Pipeline pl5 = new PipelineA(5, new WorkA());
		
		pl2.start();
		pl5.start();
	}
}
