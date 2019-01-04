package org.nico.design.mode.builder;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class Chair extends Furniture{

	@Override
	public String name() {
		return "椅子";
	}

	@Override
	public Texture texture() {
		return new Rosewood();
	}

	@Override
	public String color() {
		return "红黑色";
	}

	@Override
	public float width() {
		return 40;
	}

	@Override
	public float height() {
		return 60;
	}

	@Override
	public float length() {
		return 40;
	}

}
