package org.nico.data.structure.tree;

import java.util.Random;

public class AVLTreeTest {

	public static void main(String[] args) {
		
		int count = 30;
		int root = count/2;
		AVLTree bt = new AVLTree();
		Random random = new Random();

		int loop = count;
		while(loop -- > 0) {
			int index = random.nextInt(count);
			bt.insert(index, index);
			System.out.println("插入结点：" + index);
			System.out.println(bt);
			System.out.println();
		}
		
	}
}
