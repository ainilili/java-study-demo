package org.nico.algorithm.test;

import org.junit.Test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

public class BloomFilterTest {

    @Test
    public void guavaBloomFilter() {
        BloomFilter<Integer> filter = BloomFilter.create(Funnels.integerFunnel(), 1000 * 10000, 0.001F);
        
        long start = System.currentTimeMillis();
        for(int index = 0; index < 1000 * 100; index ++) {
            filter.put(index);
        }
        long end = System.currentTimeMillis();
        
        System.out.println(end - start);
        System.out.println(filter.mightContain(156464679));
        
        
        boolean a = true;
        a |= false;
        System.out.println(a);
    }
    
}
