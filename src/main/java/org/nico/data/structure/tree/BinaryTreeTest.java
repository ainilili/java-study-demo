package org.nico.data.structure.tree;

import java.util.Random;

public class BinaryTreeTest {

	public static void main(String[] args) {
		
		BinaryTree bt = new BinaryTree(100, "Hello BinaryTree");
		
		Random random = new Random();
		int count = 1000;
		while(count -- > 0) {
			int index = random.nextInt(100000);
			bt.insert(index, index);
		}
		
		System.out.println(bt);
	}
}
