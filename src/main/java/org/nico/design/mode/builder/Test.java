package org.nico.design.mode.builder;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class Test {

	public static void main(String[] args) {
		Room room = new Room();
		room.append(new Chair());
		room.append(new Table());
		
		System.out.println(room.info());
				
	}
}
