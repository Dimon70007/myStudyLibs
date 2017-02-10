package smartech_test;

import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 08.02.17.
 */
public class NodeHelper {

    public static int convert2DTo1D(final Point p, final int sizeX){
        return p.y==0?p.x:p.y*sizeX+p.x;
    }

    public static int[] convertMatrixToVector(final int [][] arr) {
        final int sizeX =arr.length;
        final int sizeY =arr[sizeX-1].length;
        int[] result=new int[sizeY*sizeX];
        for (int y = 0; y < sizeY; y++) {
            for (int x = 0; x < sizeX; x++) {
                result[NodeHelper.convert2DTo1D(
                        new Point(x,y),sizeX)]
                        =arr[x][y];
            }
        }
        return result;
    }

    public static int[][] convertVetorToMatrix(final int[] arr, final int sizeX){
        final int[][] result=new int[sizeX][arr.length/sizeX];
        for (int i = 0; i < arr.length; i++) {
            result[i%sizeX][i/sizeX]=arr[i];
        }
        return result;
    }
}
