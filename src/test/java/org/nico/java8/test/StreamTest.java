package org.nico.java8.test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamTest {

    @Test
    public void sorted() {
        List<Book> books = new ArrayList<Book>();
        for(int index = 0; index < 100 * 10000; index ++) {
            books.add(new Book("java", index));
        }
        
        long start = System.currentTimeMillis();
        books = books.parallelStream()
        .sorted((a, b) -> a.price < b.price ? 1 : -1)
        .collect(Collectors.toList());
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
    
    
}
