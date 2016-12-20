package homework.sorting.merge_sort;

import java.util.Arrays;

/**
 * Created by OTBA}|{HbIu` on 20.12.16.
 */
public class MergeApp {
    public static void main(String[] args) {
        int[] arA={23,47,81,95,7,14,39,55,1,62,74,25,91};
        int[] arC=new int[arA.length];

        arC= mergeSort(arA);
        display(arC,arC.length);
    }

    private static void display(int[] arC, int length) {
        System.out.println(Arrays.toString(arC));
    }

    private static int[] mergeSort(int[] arA) {
        int delimiter=(int)arA.length/2;
        int[] left=new int[delimiter];
        int[] right=new int[arA.length-delimiter];
        System.arraycopy(arA,0,left,0,delimiter);
        System.arraycopy(arA,delimiter,right,0,arA.length-delimiter);
        if (left.length>1)
            left=mergeSort(left);
        if (right.length>1)
            right=mergeSort(right);
        return merge(left,right);
    }

    private static int[] merge(int[] left, int[] right) {
        int lIndex=0,rIndex=0,resIndex=0;
        int [] result=new int[left.length+right.length];

        while (lIndex<left.length && rIndex<right.length)
            if (left[lIndex]<right[rIndex])
                result[resIndex++]=left[lIndex++];
            else
                result[resIndex++]=right[rIndex++];
        while (lIndex<left.length)
            result[resIndex++]=left[lIndex++];
        while (rIndex<right.length)
            result[resIndex++]=right[rIndex++];

        return result;
    }
}
