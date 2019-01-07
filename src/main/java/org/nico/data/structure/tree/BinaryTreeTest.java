package org.nico.data.structure.tree;

import java.util.Random;

public class BinaryTreeTest {

	public static void main(String[] args) {
		
		int count = 10;
		int root = count/2;
		BinaryTree bt = new BinaryTree(root, root);
		
		int loop = count;
		while(loop -- > 0) {
			int index = loop;
			bt.insert(index, index);
		}
		
		System.out.println("初始化树:");
		System.out.println(bt);
		
		bt.insert(12, 12);
		
		System.out.println("插入12:");
		System.out.println(bt);
		
		System.out.println("删除12:");
		bt.remove(12);
		System.out.println(bt);
		
		System.out.println("构造结点，插入4和10：");
		bt.remove(4);
		bt.insert(4, 4);
		bt.insert(10, 10);
		System.out.println(bt);
		
		System.out.println("移除结点9和3：");
		bt.remove(9);
		bt.remove(3);
		System.out.println(bt);
		
		System.out.println("删除root:");
		bt.remove(root);
		System.out.println(bt);
		
	}
	
}
