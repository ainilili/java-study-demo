package org.nico.sort.test;


import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.BucketSort;
import org.nico.sort.HeapSort;
import org.nico.sort.SleepSort;

public class BucketSortTest {

    @Test
    public void test() {

        AbstractSort sort = new BucketSort();

        int[] array = {14,13,12,11,10,9,8,7,6,5,4,3,2,1};

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
