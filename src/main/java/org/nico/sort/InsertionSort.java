package org.nico.sort;

public class InsertionSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        int len = array.length;
        for(int i = 1; i < len - 1; i ++) {
            for(int sub = 0; sub < i; sub ++) {
                int cur = array[i];
                if(cur < array[sub]) {
                    for(int start = i; start > sub; start--) {
                        array[start] = array[start - 1];
                    }
                    array[sub] = cur;
                    break;
                }
            }
        }
        return array;
    }

}
