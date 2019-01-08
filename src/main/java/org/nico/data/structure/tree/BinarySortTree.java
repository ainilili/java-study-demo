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
		//首先寻找该结点
		Node target = find(index, root);
		if(target == null) {
			//该结点为空则直接返回null
			return null;
		}else {
			//优先考虑将被移除结点的左结点连接到右结点的左分支
			Node n = target.right != null ? target.right : target.left;
			
			if(target.left != null && target.right != null) {
				Node leftLoopStarter = n;
				//寻找目标结点的右结点下的最左结点
				while(leftLoopStarter.left != null) {
					leftLoopStarter = leftLoopStarter.left;
				}
				leftLoopStarter.setLeft(target.left);
			}
			
			if(target.parent == null) {
				//只剩下root结点不允许删除
				if(target.left == null && target.right == null) {
					throw new RuntimeException("再删就没了！");
				}else {
					//当删除root结点时，优先考虑将它的右结点作为新的root结点
					root = n;
					n.parent = null;
				}
			}else {
				//移除响应的结点
				if(target.isLeft) {
					target.parent.setLeft(n);
				}else {
					target.parent.setRight(n);
				}
			}
			size --;
			return target.value;
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
		//保存树形
		StringBuilder builder = new StringBuilder();
		//按层序保留结点
		List<Object> cache = new ArrayList<>(50);
		//使用栈对树做层序遍历
		Queue<Node> queue = new LinkedBlockingQueue<Node>();
		queue.add(root);
		
		int depth = 0; //临时深度
		int maxDepth = getMaxDepth(); //最大深度
		while(! queue.isEmpty()) {
			Node cur = queue.poll();
			if(cur.left != null) {
				queue.add(cur.left);
			}else if(cur.depth() < maxDepth){
				//填补空缺
				queue.add(new Node(0, '#', cur));	
			}
			if(cur.right != null) {
				queue.add(cur.right);
			}else if(cur.depth() < maxDepth){
				//填补空缺
				queue.add(new Node(0, '#', cur));	
			}
			if(depth != cur.depth()) {
				//深度切换，将高度保存
				depth = cur.depth();
				cache.add(depth);
			}
			//加入该结点
			cache.add(cur);
		}
		//将保留结点渲染为树形
		for(int index = 0; index < cache.size(); index ++) {
			Object o = cache.get(index);
			if(o instanceof Integer) {
				builder.append(System.lineSeparator());
				builder.append(getOffset(maxDepth - (Integer)o));
			}else {
				if(index == 0) {
					builder.append(getOffset(maxDepth));
				}
				builder.append(((Node)o).value);
				builder.append(getOffset(maxDepth - ((Node)o).depth() + 1));
			}
		}
		return builder.toString();
	}
	
	/**
	 * @param height 当前结点高度 = 最大深度 - 当前结点深度
	 * @return 首距或者两结点的间隔距离 
	 */
	public String getOffset(double height) {
		StringBuilder builder = new StringBuilder();
		int count = (int) Math.pow(2d, height) - 1;
		while(count -- > 0) {
			builder.append(" ");
		}
		return builder.toString();
	}
	
	/**
	 * @return 当前树的最大深度
	 */
	public int getMaxDepth() {
		Queue<Node> queue = new LinkedBlockingQueue<Node>();
		queue.add(root);
		int depth = 0;
		while(! queue.isEmpty()) {
			Node cur = queue.poll();
			if(cur.left != null) queue.add(cur.left);
			if(cur.right != null) queue.add(cur.right);
			if(depth != cur.depth()) depth = cur.depth();
		}
		return depth;
	}
	
	
	public Object insert(int index, Object value, Node cur) {
		while(true) {
			if(cur.index == index) {
				//命中则更新
				cur.value = value;
				break;
			}else {
				if(index < cur.index) {
					if(null == cur.left) {
						//无命中则添加
						cur.left = new Node(index, value, cur);
						cur.left.isLeft = true;
						size ++;
						break;
					}else {
						//指向左结点
						cur = cur.left;
					}
				}else{
					if(null == cur.right) {
						//无命中则添加
						cur.right = new Node(index, value, cur);
						size ++;
						break;
					}else {
						//指向右结点
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
