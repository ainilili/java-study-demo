package org.nico.sort.test;

import java.util.Arrays;

import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.QuickSort;

public class QuickSortTest {

    @Test
    public void test() {

        AbstractSort sort = new QuickSort();

        //
        int[] array = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
//        int[] array = {8,5,7,3,4,1,0,100,56};
        int index = 0;
        int count = 80000;
        array = new int[count];
        while(count -- > 0) {
            array[index ++] = count;
        }

        long start = System.currentTimeMillis();
        sort.sort(array);
        
        long end = System.currentTimeMillis();
        System.out.println("time-consuming：" + (end - start) + "ms");

    }
}
