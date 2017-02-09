package smartech_test;

import java.util.Arrays;

/**
 * Created by OTBA}|{HbIu` on 08.02.17.
 */
public class Node {

    private final int[] arr;
    private final int colorsCount;

    public Node(final int [] arr, final int colorsCount) {
        this.arr=arr;
        this.colorsCount=colorsCount;
    }
    public Node(final int [][] arr, final int colorsCount) {
        this.arr=CoordinateHelper
                .convertMatrixToVector(arr);

        this.colorsCount=colorsCount;
    }
    public int[] getArr() {
        return arr;
    }

    public int[][] get2DArr(final int sizeX) {
        return CoordinateHelper.convertVetorToMatrix(arr,sizeX);
    }

    public Node nextNode(final int coordinate) {
        final int color=arr[coordinate];
        if (color<colorsCount-1){
            arr[coordinate]++;
            Node result=new Node(Arrays.copyOf(arr,arr.length),colorsCount);
            arr[coordinate]--;
            return result;
        }
        return null;
    }

    public boolean isFinal(){
        return arr[arr.length-1]>=colorsCount-1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return Arrays.equals(arr, node.arr);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }
}
