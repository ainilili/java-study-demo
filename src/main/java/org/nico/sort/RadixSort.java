package org.nico.sort;

public class RadixSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        int min = 0;
        int max = 0;
        for(int index = 0; index < array.length; index ++) {
            if(index == 0) {
                min = max = array[index];
            }else {
                int c = array[index];
                min = c < min ? c : min;
                max = c > max ? c : max;
            }
        }

        int minBit = 0;
        int maxBit = 0;
        while((min /= 10) > 0) minBit ++ ;
        while((max /= 10) > 0) maxBit ++ ;    

        Bucket[] buckets = new Bucket[10];
        for(int index = minBit; index <= maxBit; index ++) {
           int aa = index;
            
            for(int start = 0; start < array.length; start ++) {

                Bucket newBucket = new Bucket(array[start]);
                String is = String.valueOf(array[start]);
                int hash = 0;
                if(is.length() > index) {
                    hash = Integer.valueOf(is.substring(is.length() - index - 1, is.length() - index)); 
                }

                Bucket target = buckets[hash];
                if(target == null) {
                    buckets[hash] = newBucket;
                }else {
                    if(target.v > array[start]) {
                        newBucket.n = target;
                        buckets[hash] = newBucket;
                    }else {
                        boolean d = false;
                        
                        while(target.n != null) {
                            if(target.n.v > array[start]) {
                                newBucket.n = target.n;
                                target.n = newBucket;
                                d = true;
                                break;
                            }else {
                                target = target.n;
                            }
                        }
                        if(d) continue;
                        target.n = new Bucket(array[start]);
                    }
                }
            }
            int count = 0;
            for(int i = 0; i < buckets.length; i ++) {
                Bucket target = buckets[i];
                buckets[i] = null;
                while(target != null) {
                    array[count ++] = target.v;
                    target = target.n;
                }
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
