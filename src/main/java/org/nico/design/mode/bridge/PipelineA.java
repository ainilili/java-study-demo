package org.nico.design.mode.bridge;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class PipelineA extends Pipeline{

	public PipelineA(int speed, Work work) {
		super(speed, work);
	}

	@Override
	public void start() {
		new Thread(){
			@Override
			public void run(){
				while(true){
					try {
						Thread.sleep(1000L/speed);
						work.process(speed);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
	}

}
