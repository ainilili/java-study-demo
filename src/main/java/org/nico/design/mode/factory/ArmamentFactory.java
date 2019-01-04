package org.nico.design.mode.factory;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class ArmamentFactory {

	public static Armament getArmament(String name){
		if(null == name || name.equals("")){
			return null;
		}	
		if(name.equals("airplane")){
			return new Airplane();
		}else if(name.equals("tank")){
			return new Tank();
		}else if(name.equals("bomb")){
			return new Bomb();
		}else{
			return null;
		}
	}
	
}
