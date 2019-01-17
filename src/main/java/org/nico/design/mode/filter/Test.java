package org.nico.design.mode.filter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        
        AbstractFilter<Integer> evenFilter = new EvenFilter();
        AbstractFilter<Integer> negativeFilter = new NegativeFilter();
        
        List<Integer> list = new ArrayList<Integer>();
        list.addAll(Arrays.asList(1, -1, 5, 3, 4, 7, -9, 100, 27));
        
        System.out.println("Before filter:" + list);
        
        negativeFilter.filter(list);
        System.out.println("After negative filter:" + list);
        
        evenFilter.filter(list);
        System.out.println("After even filter:" + list);
        
    }
}
