package org.nico.sort.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.MergeSort;

import com.sun.scenario.effect.Merge;

public class MergeSortTest {

    @Test
    public void test() {

        AbstractSort sort = new MergeSort();

        int[] array = {1,3,2,6,5,8,7,13,12,11};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);

        array = new int[]{1};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);

        array = new int[]{3,1};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);

        array = new int[]{3,1,2};
        Arrays.stream(sort.sort(array)).forEach(System.out::println);
    }

    @Test
    public void testPerformance() {
        AbstractSort sort = new MergeSort();

        Random random = new Random();
        int count = 100 * 10000;
        int[] array = new int[count];
        for(int index = 0; index < count; index ++) {
            array[index] = random.nextInt(count);
        }
        long start = 0;
        long end = 0;
        
        start = System.currentTimeMillis();
        sort.sort(array);
        end = System.currentTimeMillis();
        System.out.println(end - start);
        
        start = System.currentTimeMillis();
        Arrays.sort(array);
        end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    
    @Test
    public void testAccuracy() {
        int count = 100 * 100000;
        int[] array = new int[count];
        int[] array1 = new int[count];
        int[] array2 = new int[count];
        
        int index = 0;
        while(count -- > 0) {
            array[index ] = count;
            array2[index ] = count;
            array1[index ++] = count;
        }
        
        AbstractSort sort = new MergeSort();
        
        long start = 0;
        long end = 0;
        
        
        start = System.currentTimeMillis();
        Stream.of(array2).sorted().findFirst();
        end = System.currentTimeMillis();
        System.out.println("stream：" + (end - start));
        
        start = System.currentTimeMillis();
        Arrays.sort(array);
        end = System.currentTimeMillis();
        System.out.println("jdk：" + (end - start));
        
        
        start = System.currentTimeMillis();
        sort.sort(array1);
        end = System.currentTimeMillis();
        System.out.println("nico：" + (end - start));
        
        
        
    }
}
