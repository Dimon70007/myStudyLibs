package smartech_test;

import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

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
        final int[][] result=new int[arr.length/sizeX][sizeX];
        for (int i = 0; i < arr.length; i++) {
            result[i/sizeX][i%sizeX]=arr[i];
        }
        return result;
    }

    public static Set<Node> treeToSet(final Node node){
        ForkJoinPool helperPool=new ForkJoinPool();
        final Helper helper=new Helper(node,0);
        final ForkJoinTask<Set<Node>> helperTask=helper.fork();
        helperPool.submit(helperTask);
        final Set<Node> nodes=new HashSet<>();
        nodes.addAll(helperTask.join());
        return nodes;
    }

    private static class Helper extends RecursiveTask<Set<Node>> {
        private final Node currentNode;
        private final int deepLevel;
        private final static int DEFAULT_DEEP_LEVEL=3;

        public Helper(final Node currentNode, final int deepLevel) {
            this.currentNode=currentNode;
            this.deepLevel=deepLevel;
        }

        @Override
        public Set<Node> compute() {
            if (!currentNode.hasChildren()){
                return new HashSet<>();
            }
            final List<ForkJoinTask<Set<Node>>> tasks=new ArrayList<>();
            final Set<Node> children=new HashSet<>();
            children.addAll(currentNode.getChildren());
            final Set<Node> otherChildren=new HashSet<>();

            for (Node child:children) {
                final Helper helper = new Helper(
                        child
                        , deepLevel + 1
                );

                if (isAsync()) {
                    tasks.add(helper.fork());
                } else {
                    otherChildren.addAll(helper.compute());
                }
            }

            if (!tasks.isEmpty()){
                for (ForkJoinTask<Set<Node>> task:tasks) {
                    otherChildren.addAll(task.join());
                }
            }
            children.addAll(otherChildren);
            return children;
        }

        private boolean isAsync() {
            return deepLevel <= DEFAULT_DEEP_LEVEL;
        }
    }
}
