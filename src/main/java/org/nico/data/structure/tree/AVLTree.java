package org.nico.data.structure.tree;

public class AVLTree extends BinarySortTree{
	
	@Override
	public Node insert(int index, Object value) {
		Node newNode = super.insert(index, value);
		rebalance((ANode)newNode);
		return newNode;
	}

	@Override
	public Node remove(int index) {
		return super.remove(index);
	}

	@Override
	public Node createNode(int index, Object value, Node parent) {
		return new ANode(index, value, parent);
	}

	private void rebalance(ANode node) {
	}
	

	class ANode extends Node{

		public ANode(int index, Object value, Node parent) {
			super(index, value, parent);
		}
		
	}
}
