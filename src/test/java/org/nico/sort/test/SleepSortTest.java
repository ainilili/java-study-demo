package org.nico.sort.test;

import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.SleepSort;

public class SleepSortTest {

    @Test
    public void test() {

        AbstractSort sort = new SleepSort();

        int[] array = {30,29,28,27,26,25,24,23,22,21,20,19,18,17,16,15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};

        long start = System.currentTimeMillis();
        sort.sort(array);
        
        long end = System.currentTimeMillis();
        System.out.println("time-consuming：" + (end - start) + "ms");

    }
}
