package org.nico.data.structure.tree;

import java.util.Random;

import org.junit.Test;

public class AVLTreeTest {

    @Test
    public void test() {
		
//		int count = 30;
//		AbstractBinaryTree bt = new AVLTree();
//		Random random = new Random();
//		
//		while(count -- > 0) {
//		    int a = random.nextInt(30);
//		    System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>" + a);
//		    bt.insert(a, a);
//		    System.out.println(bt.toString());
//		    System.out.println();
//		}
        AbstractBinaryTree bt = new AVLTree();
        int[] array = new int[] {29,8,19,26,0,9,9,25,3,3,19,21,10,20,14,20,0,1,3,3,16,9,9,11,13,25,4,25,17};
        for(int a: array) {
            bt.insert(a, a);
            System.out.println(bt);
        }
	}
}
