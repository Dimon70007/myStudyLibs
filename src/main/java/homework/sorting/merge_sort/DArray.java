package homework.sorting.merge_sort;

import java.util.Arrays;

/**
 * Created by OTBA}|{HbIu` on 20.12.16.
 */
public class DArray {

    public static void main(String[] args) {
        int size=10000000;
        int[] arA=new int[size];
//        Set<Integer> set=new HashSet<>(size*10/7);
//        Random r=new Random();
        for (int i = 0; i < arA.length; i++) {
            arA[i]= arA.length-i-1;
        }
//        for (int i = 0; i < size; i++) {
//            set.add(size-i);
//        }
//        System.out.println(set.size());
        int[] arC=new int[arA.length];
//        Arrays.sort(arA);
//        arC=arA;
        arC= mergeSort(arA);
        display(arC,arC.length);

    }

    private static void display(int[] arC, int length) {
        System.out.println(Arrays.toString(arC));
    }


    public static int[] mergeSort(int[] array) {
        return recMergeSort(array,new int[array.length],0,array.length-1);
    }


    private static int[] recMergeSort(
            int[] target
            , int[] workspace
            , int lowerBound
            , int upperBound
    ) {
        if (upperBound==lowerBound)
            return target;

        int middle=(lowerBound+upperBound)/2;

        recMergeSort(target,workspace,lowerBound,middle);

        recMergeSort(target,workspace,middle+1,upperBound);

        return merge(target,workspace,lowerBound,middle+1,upperBound);
    }

    private static int[] merge(int[] target,
                               int[] workSpace,
                               int lowPtr,
                               int highPtr,
                               int upperBound
    ) {
        //temp index
        int j=0;
        int lowerBound=lowPtr;
        int mid=highPtr-1;
        //temp count
        int n=upperBound-lowerBound+1;

        while (lowPtr<=mid && highPtr<=upperBound)
            if(target[lowPtr]<target[highPtr])
                workSpace[j++]=target[lowPtr++];
            else
                workSpace[j++]=target[highPtr++];

        while (lowPtr<=mid)
            workSpace[j++]=target[lowPtr++];

        while (highPtr<=upperBound)
            workSpace[j++]=target[highPtr++];

        for (int i = 0; i < n; i++) {
            target[lowerBound+i]=workSpace[i];
        }

        return target;
    }
}
