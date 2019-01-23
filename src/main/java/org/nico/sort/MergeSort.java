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
            if(array[0] > array[1]) doubleAscSort(array, 0, 1);
            return array;
        }
        return sort(array, 0, array.length - 1);
    }
    
    protected int[] sort(int[] array, int start, int end) {
        int half = halfIndex(array, start, end);
        if(half == -1) return array;
        int lr = half;
        int rl = half + 1;
        array = lr - start <= 1 ? doubleAscSort(array, start, lr) : sort(array, start, lr);
        array = end - rl <= 1 ? doubleAscSort(array, rl, end) : sort(array, rl, end);
        return mergeByCache(array, start, lr, rl, end);
    }
    
    protected int[] doubleAscSort(int[] array, int first, int last) {
        int a = array[first];
        int b = array[last];
        if(a > b) {
           array[last] = a;
           array[first] = b;
        }
        return array;
    }
    
}
