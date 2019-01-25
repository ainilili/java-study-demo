package org.nico.sort;

/**
 * Heap Sort
 *
 * @author nico
 */
public class HeapSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        for(int index = (array.length - 2)/2; index >=0; index --) {
            buildBig(array, index, array.length);
        }
        for(int index = 0; index < array.length; index ++) {
            swap(array, 0, array.length - index - 1);
            buildBig(array, 0, array.length - index - 1);
        }
        return array;
    }
    
    public void buildBig(int[] array, int node, int length) {
        for(int i = node * 2 + 1; i < length; i = i * 2 + 1) {
            if(i+1 < length && array[i] < array[i + 1]) i ++;
            if(array[i] < array[node])  break;
            swap(array, node, node = i);
        }
    }

}
