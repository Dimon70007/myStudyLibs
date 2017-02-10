package smartech_test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

/**
 * Created by OTBA}|{HbIu` on 09.02.17.
 */
public class Main {
    private static final int X_LENGTH=3;
    private static final int Y_LENGTH=3;
    private static final int COLORS=3;



    public static void main(String... args) throws Exception {

        final ForkJoinPool pool=new ForkJoinPool();
        final Node rootNode=new Node(arrCreator(X_LENGTH,Y_LENGTH),COLORS);
        final Builder builder=new Builder(
                rootNode
                , 0
        );
        final ForkJoinTask<Set<Node>> rootTask=builder.fork();
        pool.submit(rootTask);
        Set<Node> nodes=rootTask.join();
        System.out.println(nodes.size());
        Iterator<Node> iterator=nodes.iterator();
        for (int i = 0; i < 10; i++) {
            int [][] tmp=iterator.next().get2DArr(X_LENGTH);
            System.out.println(Arrays.deepToString(tmp));
        }
    }

    private static int [][] arrCreator(final int xLength, final int yLength){
        int[][] result=new int[xLength][yLength];
        for (int i = 0; i < xLength; i++) {
            Arrays.fill(result[i],0);
        }
        return result;
    }

}
