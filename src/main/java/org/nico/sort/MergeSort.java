package org.nico.sort;

/**
 * Merge Sort
 * 
 * @author nico
 */
public class MergeSort extends AbstractSort{
    
    @Override
    public int[] sort(int[] array) {
        if(array.length == 1) return array;
        if(array.length == 2) {
            if(array[0] > array[1]) swapAsc(array, 0, 1);
            return array;
        }
        return sort(array, 0, array.length - 1);
    }
    
    protected int[] sort(int[] array, int start, int end) {
        int half = halfIndex(array, start, end);
        if(half == -1) return array;
        int lr = half;
        int rl = half + 1;
        array = lr - start <= 1 ? swapAsc(array, start, lr) : sort(array, start, lr);
        array = end - rl <= 1 ? swapAsc(array, rl, end) : sort(array, rl, end);
        return mergeByCache(array, start, lr, rl, end);
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
