package org.nico.data.structure.tree;

public abstract class AbstractBinaryTree {

	public abstract Node insert(int index, Object value);
	
	public abstract Node remove(int index);
	
	public abstract Node get(int index);
	
	public abstract int size();
	
	protected class Node{
		
		//左叶子结点
		protected Node left;
		
		//右叶子结点
		protected Node right;
		
		//父结点
		protected Node parent;
		
		//索引
		protected int index;
		
		//内容
		protected Object value;
		
		//该值为true意味着这个结点坐落于左边
		protected boolean isLeft;
		
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
