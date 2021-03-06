package org.nico.sort.test;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

import org.junit.Test;
import org.nico.sort.AbstractSort;
import org.nico.sort.MergeSort;

public class MergeSortTest {


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
    public void testCompare() {
        
        String json1 = "{nico:zhenshuai}";
        String json2 = "{zhenshuai:nico}";
        
        AbstractSort sort = new MergeSort();
        
        int[] sort1 = sort.sort(json1.chars().map(c -> c).toArray());
        int[] sort2 = sort.sort(json2.chars().map(c -> c).toArray());
        
        for(int i: sort1) {
            System.out.print(i + " ");
        }
        System.out.println();
        for(int i: sort2) {
            System.out.print(i + " ");
        }
        System.out.println();
        
        System.out.println("json1 == json2 ? " + Arrays.equals(sort1, sort2));
    }
    
    @Test
    public void testAccuracy() {
        int count = 100 * 10000;
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
