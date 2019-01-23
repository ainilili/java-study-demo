package org.nico.sort;

public class SelectionSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        int len = array.length;
        for(int i = 0; i < len; i ++) {
            int minIndex = i;
            for(int j = i + 1; j < len; j ++) {
                if(array[i] > array[j]) {
                    minIndex = j;
                }
            }
            if(minIndex != i) {
                swapAsc(array, i, minIndex);
            }
        }
        return array;
    }

}
