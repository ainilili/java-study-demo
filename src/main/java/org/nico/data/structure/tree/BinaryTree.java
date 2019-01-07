package org.nico.data.structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTree extends AbstractTree{

	private Node root;
	
	private int size;
	
	public BinaryTree(int index, Object value) {
		this.root = new Node(index, value, null);
	}
	
	@Override
	public Object insert(int index, Object value) {
		return insert(index, value, root);
	}
	
	@Override
	public Object remove(int index) {
		Node target = find(index, root);
		if(target == null) {
			return null;
		}else {
			if(target.isLeft) {
				target.parent.left = target.right;
				Node leftLoopStarter = target.left;
				while(leftLoopStarter.left != null) {
					leftLoopStarter = leftLoopStarter.left;
				}
				leftLoopStarter.left = target.left;
				target = null; //help GC
			}else {
				target.parent.right = target.left;
				Node rightLoopStarter = target.right;
				while(rightLoopStarter.right != null) {
					rightLoopStarter = rightLoopStarter.right;
				}
				rightLoopStarter.right = target.right;
			}
			Object value = target.value;
			target = null; //help GC
			size ++;
			return value;
		}
	}

	@Override
	public Object get(int index) {
		Node target = find(index, root);
		if(target == null) {
			return null;
		}else {
			return target.value;
		}
	}
	
	@Override
	public int size() {
		return this.size;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		List<Object> cache = new ArrayList<>(50);
		Queue<Node> queue = new LinkedBlockingQueue<Node>();
		queue.add(root);
		
		int depth = 0;
		int maxDepth = getMaxDepth();
		while(! queue.isEmpty()) {
			Node cur = queue.poll();
			if(cur.left != null) {
				queue.add(cur.left);
			}else if(cur.depth < maxDepth){
				queue.add(new Node(0, '#', cur));
			}
			if(cur.right != null) {
				queue.add(cur.right);
			}else if(cur.depth < maxDepth){
				queue.add(new Node(0, '#', cur));
			}

			if(depth != cur.depth) {
				depth = cur.depth;
				cache.add(depth);
			}
			cache.add(cur);
		}
		
		for(int index = 0; index < cache.size(); index ++) {
			Object o = cache.get(index);
			if(o instanceof Integer) {
				builder.append(System.lineSeparator());
				builder.append(getOffset(depth - (Integer)o));
			}else {
				if(index == 0) {
					builder.append(getOffset(depth));
				}
				builder.append(((Node)o).value);
				builder.append(getOffset(depth - ((Node)o).depth + 1));
			}
		}
		return builder.toString();
	}
	
	public String getOffset(double height) {
		StringBuilder builder = new StringBuilder();
		int count = (int) Math.pow(2d, height) - 1;
		while(count -- > 0) {
			builder.append(" ");
		}
		return builder.toString();
	}
	
	public int getMaxDepth() {
		Queue<Node> queue = new LinkedBlockingQueue<Node>();
		queue.add(root);
		int depth = 0;
		while(! queue.isEmpty()) {
			Node cur = queue.poll();
			if(cur.left != null) {
				queue.add(cur.left);
			}
			if(cur.right != null) {
				queue.add(cur.right);
			}

			if(depth != cur.depth) {
				depth = cur.depth;
			}
		}
		return depth;
	}
	
	
	public Object insert(int index, Object value, Node cur) {
		if(index == cur.index) {
			cur.value = value;
		}else if(index < cur.index) {
			if(null == cur.left) {
				cur.left = new Node(index, value, cur);
				cur.left.isLeft = true;
				size ++;
			}else {
				insert(index, value, cur.left);
			}
		}else{
			if(null == cur.right) {
				cur.right = new Node(index, value, cur);
				size ++;
			}else {
				insert(index, value, cur.right);
			}
		}
		return value;
	}
	
	public Node find(int index, Node cur) {
		Node next = null;
		if(index == cur.index) {
			return cur;
		}else if(index < cur.index) {
			next = cur.left;
		}else{
			next = cur.right;
		}
		if(null == next) {
			return null;
		}else {
			return find(index, next);
		}
	}
	
	class Node{
		
		private Node left;
		
		private Node right;
		
		private Node parent;
		
		private int index;
		
		private int depth;
		
		private Object value;
		
		private boolean isLeft;
		
		public Node(int index, Object value, Node parent) {
			this.index = index;
			this.value = value;
			if(parent != null) {
				this.parent = parent;
				this.depth = parent.depth + 1;
			}
		}

	}

}
