package org.nico.sort;

public class BubbleSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        int len = array.length;
        for(int i = 0; i < len - 1; i ++) {
            for(int j = 0; j < len - 1 - i; j ++) {
                swapAsc(array, j, j + 1);
            }
        }
        return array;
    }

}
