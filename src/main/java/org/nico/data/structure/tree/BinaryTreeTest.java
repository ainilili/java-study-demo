package org.nico.data.structure.tree;

import java.util.Random;

public class BinaryTreeTest {

	public static void main(String[] args) {
		
		int count = 10;
		BinaryTree bt = new BinaryTree(count/2, count/2);
		
		Random random = new Random();
		
		int loop = count;
		while(loop -- > 0) {
			int index = random.nextInt(count);
			bt.insert(index, index);
		}
		
		System.out.println(bt);
	}
	
}
