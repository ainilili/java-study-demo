package org.nico.data.structure.tree;

import java.util.Random;

public class BinarySortTreeTest {

	public static void main(String[] args) {
		test3();
	}

	public static void test1() {
		int count = 50;
		BinarySortTree bt = new BinarySortTree();
		Random random = new Random();

		int loop = count;
		while(loop -- > 0) {
			int index = random.nextInt(count);
			bt.insert(index, index);
			System.out.println("插入结点：" + index);
			System.out.println(bt);
			System.out.println();
		}

		System.out.println("该树结点数：" + bt.size());

		loop = bt.size();
		while(loop -- > 1) {
			int index = random.nextInt(count);
			bt.remove(index);
			System.out.println("删除结点：" + index);
			System.out.println(bt);
			System.out.println();
		}

		System.out.println("该树结点数：" + bt.size());
	}


	public static void test2() {
		int count = 10;
		int root = count/2;
		BinarySortTree bt = new BinarySortTree();

		int loop = count;
		while(loop -- > 0) {
			bt.insert(loop, loop);
		}

		System.out.println(bt);

		bt.remove(7);

		System.out.println(bt);

		bt.insert(10, 10);
		bt.insert(11, 11);

		System.out.println(bt);

		bt.remove(10);

		System.out.println(bt);

		bt.remove(4);

		System.out.println(bt);

		bt.remove(9);

		System.out.println(bt);
		
		bt.remove(5);
		
		System.out.println(bt);
	}
	
	public static void test3() {
		BinarySortTree bt = new BinarySortTree();
		bt.insert(4, 4);
		bt.insert(2, 2);
		bt.insert(3, 3);
		bt.insert(1, 1);
		bt.insert(6, 6);
		bt.insert(5, 5);
		bt.insert(7, 7);
		bt.insert(12, 12);
		bt.insert(10, 10);
		bt.insert(11, 11);
		bt.insert(16, 16);
		bt.insert(14, 14);
		bt.insert(17, 17);

		System.out.println(bt);
		
		bt.remove(1);
		System.out.println(bt);
		
		bt.remove(2);
		System.out.println(bt);
		
		bt.remove(4);
		System.out.println(bt);
		
		bt.remove(16);
		System.out.println(bt);
		
		bt.remove(8);
		System.out.println(bt);

	}
	
	
}
