package homework.sorting.merge_sort;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by OTBA}|{HbIu` on 20.12.16.
 */
public class DArray {

    public static void main(String[] args) {
        int[] arA=new int[10000000];
        Random r=new Random();
        for (int i = 0; i < arA.length; i++) {
            arA[i]= arA.length-i-1;
        }
        int[] arC=new int[arA.length];
        Arrays.sort(arA);
        arC=arA;
//        arC= mergeSort(arA);
        display(arC,arC.length);

    }

    private static void display(int[] arC, int length) {
        System.out.println(Arrays.toString(arC));
    }


    public static void mergeSort(int[] array) {
        array=recMergeSort(array,new int[array.length],0,array.length);
    }


    private static int[] recMergeSort(int[] target, int[] tmp, int leftLimit, int rightLimit) {
        if (rightLimit-leftLimit<=1)
            return target;
        int middle=(leftLimit+rightLimit)/2;
        recMergeSort(target,tmp,leftLimit,middle);
        recMergeSort(target,tmp,middle+1,rightLimit);

        return merge(target,tmp,leftLimit,middle,rightLimit);
    }

    private static int[] merge(int[] target, int[] workSpace, int lowPtr, int highPtr, int upperBound) {
        //temp index
        int j=0;
        int lowerBound=lowPtr;
        int mid=highPtr;
        //temp count
        int n=upperBound-lowPtr+1;
        while (lowPtr<mid && highPtr<upperBound)
            if(target[lowPtr]<target[highPtr])
                workSpace[j++]=target[lowPtr++];
            else
                workSpace[j++]=target[highPtr];

        while (lowPtr<mid)
            workSpace[j++]=target[lowPtr++];

        while (highPtr<upperBound)
            workSpace[j++]=target[highPtr++];

        return workSpace;
    }
}
