package org.nico.design.mode.test;

import org.junit.Test;
import org.nico.design.mode.bridge.Pipeline;
import org.nico.design.mode.bridge.PipelineA;
import org.nico.design.mode.bridge.WorkA;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class BridgeTest {

    @Test
    public void test() {
		
		Pipeline pl2 = new PipelineA(2, new WorkA());
		Pipeline pl5 = new PipelineA(5, new WorkA());
		
		pl2.start();
		pl5.start();
	}
}
