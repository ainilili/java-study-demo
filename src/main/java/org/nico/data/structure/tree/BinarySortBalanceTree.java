package org.nico.data.structure.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySortBalanceTree extends AbstractBinaryTree{

	//root结点
	private Node root;
	
	//size
	private int size;
	
	//min depth node
	private Node minDepthNode;
	
	public BinarySortBalanceTree(int index, Object value) {
		this.root = new Node(index, value, null);
		this.size = 1;
		this.minDepthNode = this.root;
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
			Node n = target.rightChild != null ? target.rightChild : target.leftChild;
			
			if(target.leftChild != null && target.rightChild != null) {
				Node leftChildLoopStarter = n;
				//寻找目标结点的右结点下的最左结点
				while(leftChildLoopStarter.leftChild != null) {
					leftChildLoopStarter = leftChildLoopStarter.leftChild;
				}
				leftChildLoopStarter.setLeft(target.leftChild);
			}
			
			if(target.parent == null) {
				//只剩下root结点不允许删除
				if(target.leftChild == null && target.rightChild == null) {
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
			if(cur.leftChild != null) {
				queue.add(cur.leftChild);
			}else if(cur.depth() < maxDepth){
				//填补空缺
				queue.add(new Node(0, '#', cur));	
			}
			if(cur.rightChild != null) {
				queue.add(cur.rightChild);
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
			if(cur.leftChild != null) queue.add(cur.leftChild);
			if(cur.rightChild != null) queue.add(cur.rightChild);
			if(depth != cur.depth()) depth = cur.depth();
		}
		return depth;
	}
	
	
	public Object insert(int index, Object value, Node cur) {
		Node newNode = null;
		while(true) {
			if(cur.index == index) {
				//命中则更新
				cur.value = value;
				break;
			}else {
				if(index < cur.index) {
					if(null == cur.leftChild) {
						//无命中则添加
						newNode = cur.leftChild = new Node(index, value, cur);
						cur.leftChild.isLeft = true;
						size ++;
						break;
					}else {
						//指向左结点
						cur = cur.leftChild;
					}
				}else{
					if(null == cur.rightChild) {
						//无命中则添加
						newNode = cur.rightChild = new Node(index, value, cur);
						size ++;
						break;
					}else {
						//指向右结点
						cur = cur.rightChild;
					}
				}
			}
		}
		if(newNode != null) {
			
			counterBalance(newNode);
		}
		return value;
	}
	
	public void updateMinDepthNode() {
		
	}
	
	public void counterBalance(Node newNode) {
		
	}
	
	public Node find(int index, Node cur) {
		while(cur != null && cur.index != index) {
			cur = index < cur.index ? cur.leftChild : cur.rightChild;
		}
		return cur;
	}
	
	class Node{
		
		//左叶子结点
		private Node leftChild;
		
		//右叶子结点
		private Node rightChild;
		
		//左结点
		private Node leftNode;
		
		//右结点
		private Node rightNode;
		
		//父结点
		private Node parent;
		
		//索引
		private int index;
		
		//内容
		private Object value;
		
		//该值为true意味着这个结点坐落于左边
		private boolean isLeft;
		
		//深度
		private int depth;
		
		public Node(int index, Object value, Node parent) {
			this.index = index;
			this.value = value;
			if(parent != null) {
				this.parent = parent;
				this.depth = parent.depth + 1;
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
		 * @param leftChild 左结点
		 */
		public void setLeft(Node leftChild) {
			this.leftChild = leftChild;
			if(leftChild != null) {
				this.leftChild.parent = this;
				this.leftChild.isLeft = true;
			}
		}
		
		/**
		 * 为右结点赋值
		 * 
		 * @param rightChild 右结点
		 */
		public void setRight(Node rightChild) {
			this.rightChild = rightChild;
			if(rightChild != null) this.rightChild.parent = this;
		}
	}
}
