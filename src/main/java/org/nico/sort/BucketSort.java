package org.nico.sort;

public class BucketSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        int min = 0;
        int max = 0;
        int len = array.length;
        if(len == 1 ) return array;
        
        for(int index = 0; index < len; index ++) {
            if(index == 0) {
                min = max = array[index];
            }else {
                int c = array[index];
                min = c < min ? c : min;
                max = c > max ? c : max;
            }
        }
        
        int c = 0;
        int t = max;
        while((t = (t >>> 1)) > 0) {
            c ++;
        }
        int size = max >>> c/2;
        
        Bucket[] buckets = new Bucket[size];
        
        int diff = max - min + 1;
        
        int interval = diff / size;
        
        for(int index = 0; index < array.length; index ++) {
            Bucket newBucket = new Bucket(array[index]);
            int rel = array[index] - min;
            int hash = rel / interval;
            if(hash == size) hash --;
            
            Bucket target = buckets[hash];
            if(target == null) {
                buckets[hash] = newBucket;
            }else {
                if(target.v > array[index]) {
                    newBucket.n = target;
                    buckets[hash] = newBucket;
                }else {
                    boolean d = false;
                    while(target.n != null) {
                        target = target.n;
                        if(target.v > array[index]) {
                            newBucket.n = target;
                            buckets[hash] = newBucket;
                            d = true;
                            break;
                        }
                    }
                    if(d) continue;
                    target.n = new Bucket(array[index]);
                }
            }
        }
        int count = 0;
        for(int index = 0; index < buckets.length; index ++) {
            Bucket target = buckets[index];
            while(target != null) {
                array[count ++] = target.v;
                target = target.n;
            }
        }
        return array;
    }
    
    class Bucket{
        int v;
        Bucket n;
        public Bucket(int v) {
            super();
            this.v = v;
        }
    }
    
}
