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
    
    protected int[] swapAsc(int[] array, int first, int last) {
        int a = array[first];
        int b = array[last];
        if(a > b) {
           array[last] = a;
           array[first] = b;
        }
        return array;
    }
    
}
