package org.nico.sort;

public class QuickSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    public void quickSort(int[] array, int left, int right) {
        if(left >= right) return;
        int basic = array[left];
        int l = left;
        int r = right;
        while(l < r) {
            while(l < r && array[r] > basic) -- r;
            while(l < r && array[l] <= basic) ++ l;
            swap(array, l, r);
        }
        swap(array, left, l);
        quickSort(array, left, l - 1);
        quickSort(array, l + 1, right);
    }
    


}
