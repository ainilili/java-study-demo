package org.nico.design.mode.test;

import org.nico.design.mode.builder.Chair;
import org.nico.design.mode.builder.Room;
import org.nico.design.mode.builder.Table;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class BuilderTest {

	public static void main(String[] args) {
		Room room = new Room();
		room.append(new Chair());
		room.append(new Table());
		
		System.out.println(room.info());
				
	}
}
