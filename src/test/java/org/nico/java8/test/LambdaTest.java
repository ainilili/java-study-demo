package org.nico.java8.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.nico.java8.lambda.NicoStream;

public class LambdaTest {

    @Test
    public void test() {
        
        List<Book> books = new ArrayList<Book>();
        books.add(new Book("Java", 50));
        books.add(new Book("Java", 100));
        books.add(new Book("Java", 75));
        
        new NicoStream<Book>(books)
            .sorted((a, b) -> a.price > b.price ? 1 : -1)
            .filter(b -> b.price == 50)
            .foreach(System.out::println);
    }
}
