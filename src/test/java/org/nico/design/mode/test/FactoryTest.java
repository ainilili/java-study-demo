package org.nico.design.mode.test;

import org.junit.Test;
import org.nico.design.mode.factory.Armament;
import org.nico.design.mode.factory.ArmamentFactory;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class FactoryTest {

    @Test
    public void test() {
		
		Armament armament = ArmamentFactory.getArmament("airplane");
		System.out.println(armament.name());
		
		armament = ArmamentFactory.getArmament("tank");
		System.out.println(armament.name());
		
		armament = ArmamentFactory.getArmament("bomb");
		System.out.println(armament.name());
	}
}
