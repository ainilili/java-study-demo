package org.nico.java8.stream;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class NicoStream<T> {

    private List<T> list;
    
    public NicoStream(List<T> list) {
        this.list = list;
    }
    
    public NicoStream<T> foreach(Consumer<T> callback){
        for(T t: list) callback.accept(t);
        return this;
    }
    
    public NicoStream<T> sorted(Comparator<T> comparator){
        Collections.sort(list, comparator);
        return this;
    }
    
    public NicoStream<T> filter(Predicate<T> predicate){
        Iterator<T> its = list.iterator();
        while(its.hasNext()) {
            if(! predicate.test(its.next())) its.remove();
        }
        return this;
    }
    
    public List<T> list(){
        return this.list;
    }
    
    
    
    
    
}
