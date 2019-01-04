package org.nico.design.mode.builder;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class Table extends Furniture{

	@Override
	public String name() {
		return "桌子";
	}

	@Override
	public Texture texture() {
		return new Solidwood();
	}

	@Override
	public String color() {
		return "木黄色";
	}

	@Override
	public float width() {
		return 200;
	}

	@Override
	public float height() {
		return 120;
	}

	@Override
	public float length() {
		return 200;
	}

}
