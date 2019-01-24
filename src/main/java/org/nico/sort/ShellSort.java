package org.nico.sort;

/**
 * Shell Sort
 * -> Group Insertion Sort
 * 
 * @author nico
 */
public class ShellSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        int len = array.length;
        int interval = len;
        while(interval != 1) {
            interval >>>= 1;
            for(int start = 0; start < interval; start ++) {
                for(int a = start + interval; a < len; a = a + interval) {
                    if(array[a] < array[a - interval]) {
                        for(int b = a; b >= interval; b = b - interval) {
                            if(array[b] < array[b - interval]) {
                                swapAsc(array, b - interval, b);
                            }else {
                                break;
                            }
                        }
                    }
                }
            }
        }
        return array;
    }

}
