package org.nico.design.mode.factory;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class Test {

	public static void main(String[] args) {
		
		Armament armament = ArmamentFactory.getArmament("airplane");
		System.out.println(armament.name());
		
		armament = ArmamentFactory.getArmament("tank");
		System.out.println(armament.name());
		
		armament = ArmamentFactory.getArmament("bomb");
		System.out.println(armament.name());
	}
}
