package org.nico.java8.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class StreamTest {

    @Test
    public void sorted() {
        List<Book> books = new ArrayList<Book>();
        for(int index = 0; index < 100; index ++) {
            books.add(new Book("java", index));
        }
        
        books.parallelStream()
        .sorted((a, b) -> a.price < b.price ? 1 : -1)
        .filter(a -> a.price % 2 == 0)
        .forEach(System.out::println);
        
    }
    
    
}
