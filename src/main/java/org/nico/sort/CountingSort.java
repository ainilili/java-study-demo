package org.nico.sort;

public class CountingSort extends AbstractSort{

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
        
        int len = max - min + 1;
        
        if(len == 1 ) return array;
        
        int[] table = new int[len];
        for(int index = 0; index < array.length; index ++) {
            table[array[index] - min] ++;
        }
        int count = 0;
        for(int index = 0; index < table.length; index ++) {
            for(int i = 0; i < table[index]; i++) {
                array[count ++] = index + min;
            }
        }
        return array;
    }

    
}
