package org.nico.sort;

import java.util.Stack;

public class QuickSort extends AbstractSort{

    @Override
    public int[] sort(int[] array) {
//        quickSortDepth(array, 0, array.length - 1);
        quickSortBreadth(array);
        return array;
    }

    public void quickSortDepth(int[] array, int left, int right) {
        if(left >= right) return;
        pickMiddle(array, left, right);
        int basic = array[left];
        int l = left;
        int r = right;
        while(l < r) {
            while(l < r && array[r] > basic) -- r;
            while(l < r && array[l] <= basic) ++ l;
            swap(array, l, r);
        }
        swap(array, left, l);
        quickSortDepth(array, left, l - 1);
        quickSortDepth(array, l + 1, right);
    }
    
    public void quickSortBreadth(int[] array) {
        Stack<QuickSortPlan> stack = new Stack<QuickSort.QuickSortPlan>();
        stack.add(new QuickSortPlan(0, array.length - 1));
        
        while(! stack.isEmpty()) {
            QuickSortPlan qsp = stack.pop();
            int left = qsp.left;
            int right = qsp.right;
            
            if(left >= right) continue;
            pickMiddle(array, left, right);
            int basic = array[left];
            int l = left;
            int r = right;
            while(l < r) {
                while(l < r && array[r] > basic) -- r;
                while(l < r && array[l] <= basic) ++ l;
                swap(array, l, r);
            }
            swap(array, left, l);
            
            stack.add(new QuickSortPlan(left, l - 1));
            stack.add(new QuickSortPlan(l + 1, right));
        }
    }
    
    
    class QuickSortPlan{
        int left;
        int right;
        public QuickSortPlan(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
    
    public void pickMiddle(int[] array, int left, int right) {
        int leftValue = array[left];
        int rightValue = array[right];
        int mid = (left + ((right - left + 1) >>> 1)) - 1;
        int midValue = array[mid];
        int midIndex = 0;
        if(leftValue > rightValue) {
            midIndex = rightValue > midValue ? right : mid; 
        }else {
            midIndex = leftValue > midValue ? left : mid;
        }
        swap(array, left, midIndex);
    }


}
