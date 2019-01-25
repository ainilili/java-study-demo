package org.nico.sort.test;


import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.HeapSort;
import org.nico.sort.SleepSort;

public class HeapSortTest {

    @Test
    public void test() {

        AbstractSort sort = new HeapSort();

//        int[] array = {8,6,7,3,2,10,9,100,70,56,38};
        int[] array = {14,13,12,11,10,9,8,7,6,5,4,3,2,1};
//        int[] array = {1,2,3,4,5,6,7,8,9};

        long start = System.currentTimeMillis();
        sort.sort(array);
        for(int index = 0; index < array.length; index ++) {
            System.out.print(array[index] + " ");
        }
        System.out.println();
        
        long end = System.currentTimeMillis();
        System.out.println("time-consuming：" + (end - start) + "ms");

    }
}
