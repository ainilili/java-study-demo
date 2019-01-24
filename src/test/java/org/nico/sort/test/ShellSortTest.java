package org.nico.sort.test;

import java.util.Arrays;

import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.ShellSort;

public class ShellSortTest {

    @Test
    public void test() {

        AbstractSort sort = new ShellSort();

        //        int[] array = {5,3,7,9,1,6,4,8,2};
        //        int[] array = {49,38,65,97,76,13,27,49,55,04};
        int[] array = {15,14,13,12,11,10,9,8,7,6,5,4,3,2,1};
        //        Arrays.stream(sort.sort(array)).forEach(System.out::println);


        int index = 0;
        int count = 100 * 10000;
        array = new int[count];
        while(count -- > 0) {
            array[index ++] = count;
        }

        long start = System.currentTimeMillis();
        sort.sort(array);
        long end = System.currentTimeMillis();
        System.out.println("time-consuming：" + (end - start) + "ms");
        
        for(int i = 0; i < 100; i ++) {
            System.out.print(array[i] + " ");
        }
    }
}
