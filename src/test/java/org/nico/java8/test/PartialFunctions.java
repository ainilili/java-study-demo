package org.nico.java8.test;

import java.util.function.BiFunction;
import java.util.function.Function;

public class PartialFunctions {
    private static  <T, U, R> Function<U, R> partialLeft(BiFunction<T, 
            U, R> biFunction, T t) {
        return (u) -> biFunction.apply(t, u);
    }

    private static  <T, U, R> Function<T, R> partialRight(BiFunction<T, 
            U, R> biFunction, U u) {
        return (t) -> biFunction.apply(t, u);
    }
    
    
    public static void main(String[] args) {
        BiFunction<Integer, Integer, Integer> biFunction = (v1, v2) -> v1 
                - v2;
        Function<Integer, Integer> subtractFrom10 = 
                partialLeft(biFunction, 10);
        Function<Integer, Integer> subtractBy10 = partialRight(biFunction, 
                10);
        System.out.println(subtractFrom10.apply(5)); // 5
        System.out.println(subtractBy10.apply(5));   // -5
    }
}