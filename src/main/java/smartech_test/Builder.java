package smartech_test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by OTBA}|{HbIu` on 08.02.17.
 */
public class Builder extends RecursiveTask<Set<Node>> {
//    private static int counter=0;
    private final Node currentNode;
    private final int deepLevel;
//    private final int sizeY;
//    private final int sizeX;
    private final static int DEFAULT_DEEP_LEVEL=3;
//    private final Node nextNode;
//    private final Point currentPoint;

    public Builder(
              final Node currentNode
              , final int deepLevel
//              , final int sizeX
//              , final int sizeY
            ) {

        this.currentNode=currentNode;
        this.deepLevel=deepLevel;
//        this.sizeX=sizeX;
//        this.sizeY=sizeY;

    }

    @Override
    public Set<Node> compute() {
//        System.out.println(++counter);
        final List<ForkJoinTask<Set<Node>>> tasks=new ArrayList<>();
        final Set<Node> finalNodes=new HashSet<>();

        finalNodes.add(currentNode);
        if (!currentNode.isFinal()) {
            final int arrLength=currentNode.getLength();
            for (int i = 0; i < arrLength; i++) {
                Node nextNode = currentNode.nextNode(i);
                if (nextNode == null)
                    continue;

                final Builder builder = new Builder(
                        nextNode
                        , deepLevel + 1
//                        , sizeX
//                        , sizeY
                );
                if (isAsync()) {
                    tasks.add(builder.fork());
                } else {
                    finalNodes.addAll(builder.compute());
                }
            }
        }
        if (!tasks.isEmpty()){
            for (ForkJoinTask<Set<Node>> task:tasks) {
                finalNodes.addAll(task.join());
            }
        }
        return finalNodes;
    }

    private boolean isAsync() {
        if (deepLevel > DEFAULT_DEEP_LEVEL) {
            return false;
        } else {
            return true;
        }
    }
}
