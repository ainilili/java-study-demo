package org.nico.java8.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Test;

public class StreamTest {

    @Test
    public void sorted() {
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Java", 50));
        books.add(new Book("Java", 100));
        books.add(new Book("Java", 75));
        books.add(new Book("C#", 79));
        books.add(new Book("C#", 58));
        books.add(new Book("C#", 27));
        books.add(new Book("PHP", 130));
        books.add(new Book("PHP", 10));
        books.add(new Book("PHP", 39));
        
        
        Map<String, Optional<Book>> map = books.stream()
        .sorted((a, b) -> a.name.compareTo(b.name))        
        .sorted((a, b) -> a.price > b.price ? 1 : -1)
        .collect(Collectors.groupingBy(Book::getName, Collectors.maxBy((a, b) -> a.price > b.price ? 1 : -1)));
        System.out.println(map);
        
    }
    
    
}
