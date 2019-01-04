package org.nico.design.mode.builder;

import java.util.ArrayList;
import java.util.List;

/** 
 * 
 * @author nico
 * @email ainililia@163.com
 */

public class Room {

	private List<Furniture> furnitures;
	
	public Room(){
		this.furnitures = new ArrayList<>();
	}
	
	public final void append(Furniture f){
		this.furnitures.add(f);
	}
	
	public final String info(){
		final StringBuilder info = new StringBuilder();
		furnitures.forEach(f -> {
			info.append("##################" + System.lineSeparator());
			info.append("名称：" + f.name() + System.lineSeparator());
			info.append("颜色：" + f.color() + System.lineSeparator());
			info.append("材质：" + f.texture().name() + System.lineSeparator());
			info.append("长：" + f.length() + System.lineSeparator());
			info.append("宽：" + f.width() + System.lineSeparator());
			info.append("高：" + f.height() + System.lineSeparator());
		});
		return info.toString();
	}
}
