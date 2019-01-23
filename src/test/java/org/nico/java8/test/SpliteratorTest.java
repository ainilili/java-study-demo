package org.nico.java8.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Spliterator;

import org.junit.Test;

public class SpliteratorTest {

    
    @Test
    public void testListSpliterator() {
        List<Integer> list = new ArrayList<Integer>();
        for(int index = 0; index < 100; index ++) {
            list.add(index);
        }
        
        Spliterator<Integer> sp = list.spliterator();
        
        sp.trySplit();
        System.out.println("=============");
        
        sp.trySplit();
        System.out.println("=============");
        
        sp.trySplit().forEachRemaining(System.out::println);
        System.out.println("=============");
    }
}
