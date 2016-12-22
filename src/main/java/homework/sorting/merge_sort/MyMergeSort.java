package homework.sorting.merge_sort;

/**
 * Created by OTBA}|{HbIu` on 22.12.16.
 */
public class MyMergeSort {
    private static int count=0;

    public static int[] sort(final int[] arr) {

        return mergeSort(arr,new int[arr.length],0,arr.length);
    }

    private static int[] mergeSort(final int[] targetArr
            , final int[] resultArr
            , final int leftLimit
            , final int rightLimit) {
        if (rightLimit-leftLimit<=1)
            return resultArr;//тут вообще неважно что возвращать - главное прервать цикл
        int middle=(leftLimit+rightLimit)/2;
//        System.out.println(++count);

        mergeSort(targetArr,resultArr,leftLimit, middle);
//        System.out.println(++count);

        mergeSort(targetArr,resultArr,middle,rightLimit);
//        System.out.println(++count);

        return recMerge(targetArr,resultArr,leftLimit,middle,rightLimit);
    }

    private static int[] recMerge(final int[] targetArr
            , final int[] resultArr
            , final int leftLimit
            , final int middle
            , final int rightLimit) {

        int leftIndex=leftLimit;
        int rightIndex=middle;
        int resultArrIndex=leftLimit;

        while (leftIndex<middle && rightIndex<rightLimit){
            System.out.println(++count);
            if (targetArr[leftIndex]<targetArr[rightIndex]){
                resultArr[resultArrIndex++]=targetArr[leftIndex++];
            } else {
                resultArr[resultArrIndex++]=targetArr[rightIndex++];
            }
        }

        while (leftIndex<middle) {
            resultArr[resultArrIndex++] = targetArr[leftIndex++];
            System.out.println(++count);
        }
        while (rightIndex<rightLimit) {
            resultArr[resultArrIndex++] = targetArr[rightIndex++];
            System.out.println(++count);
        }
        for (int i = leftLimit; i < rightLimit; i++) {
            targetArr[i]=resultArr[i];
            System.out.println(++count);
        }
        return targetArr;
    }
}
