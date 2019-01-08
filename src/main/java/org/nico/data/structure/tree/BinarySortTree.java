package org.nico.data.structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySortTree extends AbstractBinaryTree{
	
	//root结点
	private Node root;
	
	//size
	private int size;
	
	public BinarySortTree(int index, Object value) {
		this.root = new Node(index, value, null);
		this.size = 1;
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
			Node n = target.right != null ? target.right : target.left;
			
			if(target.left != null && target.right != null) {
				Node leftLoopStarter = n;
				while(leftLoopStarter.left != null) {
					leftLoopStarter = leftLoopStarter.left;
				}
				leftLoopStarter.setLeft(target.left);
			}
			
			if(target.parent == null) {
				if(target.left == null && target.right == null) {
					throw new RuntimeException("再删就没了！");
				}else {
					root = n;
					n.parent = null;
				}
			}else {
				if(target.isLeft) {
					target.parent.setLeft(n);
				}else {
					target.parent.setRight(n);
				}
			}
			Object value = target.value;
			target = null; //help GC
			size --;
			return value;
		}
	}

	@Override
	public Object get(int index) {
		Node target = find(index, root);
		return target == null ? null : target.value;
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
			}else if(cur.depth() < maxDepth){
				queue.add(new Node(0, '#', cur));
			}
			if(cur.right != null) {
				queue.add(cur.right);
			}else if(cur.depth() < maxDepth){
				queue.add(new Node(0, '#', cur));
			}

			if(depth != cur.depth()) {
				depth = cur.depth();
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
				builder.append(getOffset(depth - ((Node)o).depth() + 1));
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

			if(depth != cur.depth()) {
				depth = cur.depth();
			}
		}
		return depth;
	}
	
	
	public Object insert(int index, Object value, Node cur) {
		while(true) {
			if(cur.index == index) {
				cur.value = value;
				break;
			}else {
				if(index < cur.index) {
					if(null == cur.left) {
						cur.left = new Node(index, value, cur);
						cur.left.isLeft = true;
						size ++;
						break;
					}else {
						cur = cur.left;
					}
				}else{
					if(null == cur.right) {
						cur.right = new Node(index, value, cur);
						size ++;
						break;
					}else {
						cur = cur.right;
					}
				}
			}
		}
		return value;
	}
	
	public Node find(int index, Node cur) {
		while(cur != null && cur.index != index) {
			cur = index < cur.index ? cur.left : cur.right;
		}
		return cur;
	}
	
	class Node{
		
		//左叶子结点
		private Node left;
		
		//右叶子结点
		private Node right;
		
		//父结点
		private Node parent;
		
		//索引
		private int index;
		
		//内容
		private Object value;
		
		//该值为true意味着这个结点坐落于左边
		private boolean isLeft;
		
		public Node(int index, Object value, Node parent) {
			this.index = index;
			this.value = value;
			if(parent != null) {
				this.parent = parent;
			}
		}
		
		/**
		 * @return 该结点深度
		 */
		public int depth() {
			int depth = 0;
			Node pre = this.parent;
			while(pre != null) {
				depth ++;
				pre = pre.parent;
			}
			return depth;
		}
		
		/**
		 * 为左结点赋值
		 * 
		 * @param left 左结点
		 */
		public void setLeft(Node left) {
			this.left = left;
			if(left != null) {
				this.left.parent = this;
				this.left.isLeft = true;
			}
		}
		
		/**
		 * 为右结点赋值
		 * 
		 * @param right 右结点
		 */
		public void setRight(Node right) {
			this.right = right;
			if(right != null) this.right.parent = this;
		}
	}

}
