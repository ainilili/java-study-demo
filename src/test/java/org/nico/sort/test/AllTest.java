package org.nico.sort.test;

import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.BubbleSort;
import org.nico.sort.BucketSort;
import org.nico.sort.CountingSort;
import org.nico.sort.HeapSort;
import org.nico.sort.InsertionSort;
import org.nico.sort.MergeSort;
import org.nico.sort.QuickSort;
import org.nico.sort.RadixSort;
import org.nico.sort.SelectionSort;
import org.nico.sort.ShellSort;

public class AllTest {

    
    @Test
    public void test() {

//        AbstractSort sort = new MergeSort();
//        AbstractSort sort = new BubbleSort();
//        AbstractSort sort = new SelectionSort();
//        AbstractSort sort = new InsertionSort();
//        AbstractSort sort = new ShellSort();
//        AbstractSort sort = new QuickSort();
//        AbstractSort sort = new CountingSort();
//        AbstractSort sort = new HeapSort();
//        AbstractSort sort = new BucketSort();
        AbstractSort sort = new RadixSort();
        
        int[] array = {1,3,2,6,5,8,7,13,12,11};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);

        array = new int[]{1};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);

        array = new int[]{3,1};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);

        array = new int[]{3,1,2};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);
        
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
        
        for(int i = 0; i< 100; i ++) {
            System.out.print(array[i] +  " ");
        }
    }
}
