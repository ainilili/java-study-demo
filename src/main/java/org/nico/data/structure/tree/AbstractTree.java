package org.nico.data.structure.tree;

public abstract class AbstractTree {

	public abstract Object insert(int index, Object value);
	
	public abstract Object remove(int index);
	
	public abstract Object get(int index);
	
	public abstract int size();
	
}
