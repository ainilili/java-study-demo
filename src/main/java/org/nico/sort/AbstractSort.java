package org.nico.sort;

public abstract class AbstractSort {

    /**
     * Sort by asc
     * 
     * @param array int array
     * @return sorted array
     */
    public abstract int[] sort(int[] array);
    
    protected int halfIndex(int[] array, int start, int end) {
        int diff = end - start;
        if(diff < 1) return -1;
        return (start + ((diff + 1) >>> 1)) - 1;
    }
    
    /**
     * Merage b cache
     * 
     */
    protected int[] mergeByCache(int[] array, int ll, int lr, int rl, int rr) {
        if(array[rl] >= array[lr]) return array;
        int[] cache = new int[rr - ll + 1];
        if(array[rr] <= array[ll]) {
            int index = 0;
            for(int a = rl; a <= rr; a ++) {
                cache[index ++] = array[a];
            }
            for(int a = ll; a <= lr; a ++) {
                cache[index ++] = array[a];
            }
        }else {
            int llen = lr - ll + 1;
            int rlen = rr - rl + 1;
            int lindex = 0;
            int rindex = 0;
            int cindex = 0;
            for(int index = 0; index < llen + rlen; index ++) {
                Integer lvalue = null;
                Integer rvalue = null;
                if(lindex < llen) lvalue = array[lindex + ll];
                if(rindex < rlen) rvalue = array[rindex + rl];
                if(null != lvalue && null != rvalue){
                    cache[cindex ++] = lvalue > rvalue ? array[rindex++ + rl] : array[lindex++ + ll];
                }else {
                    cache[cindex ++] = lvalue == null ? array[rindex++ + rl] : array[lindex++ + ll];
                }
            }
        }
        for(int index = 0; index < cache.length; index ++) {
            array[ll + index] = cache[index];
        }
        return array;
    }
    
    
}
