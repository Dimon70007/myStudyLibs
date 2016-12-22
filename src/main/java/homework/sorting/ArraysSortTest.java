package homework.sorting;

import homework.sorting.merge_sort.MyMergeSort;

import java.util.Arrays;

/**
 * Created by OTBA}|{HbIu` on 22.12.16.
 */
public class ArraysSortTest {

    public static void main(String[] args) {
        int size=64;
        int[] arr=new int[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=arr.length-i;
        }
        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.toString(MyMergeSort.sort(arr)));

    }
}
