package org.nico.java8.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.junit.Test;
import org.nico.java8.lambda.Calculator;

public class LambdaTest {

    @Test
    public void testPreJava8() {
        int[] array = {7, -2, 3, 5, -9, 3, -5, -1, 6, 8, 20};
        List<Integer> list = new ArrayList<Integer>();
        //过滤负数
        for(int i: array) {
            if(i >= 0) list.add(i);
        }
        //排序
        Collections.sort(list);

        for(int i: list) {
            System.out.println(i);
        }
    }

    @Test
    public void testAfterJava8() {
        int[] array = {7, -2, 3, 5, -9, 3, -5, -1, 6, 8, 20};
        Arrays.stream(array)
            .filter(a -> a >= 0)    //过滤
            .sorted()               //排序
            .forEach(System.out::println);
    }
    
    @Test
    public void testLambdaFilter() {
        int a = 1;
        int b = -1;
        Predicate<Integer> predicate =  i -> i >= 0;
        System.out.println(predicate.test(a));  //true
        System.out.println(predicate.test(b));  //false
    }

    @Test
    public void testInterfaceLambda() {
        BinaryOperator<Integer> bo = (a, b) -> a + b;

        Calculator cal = (a, b) -> a + b;
        System.out.println(cal.calculate(1, 2));
        System.out.println(bo.apply(1, 2));
        Calculator calculator = new Calculator() {
            @Override
            public int calculate(int a, int b) {
                return a + b;
            }
        };
        
        Comparator<Integer> comparator = (o1, o2) -> o1 > o2 ? 1 : -1;
    }
}
