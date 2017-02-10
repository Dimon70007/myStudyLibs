package smartech_test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

/**
 * Created by OTBA}|{HbIu` on 08.02.17.
 */
public class Builder extends RecursiveTask<Set<Node>> {
//    private static int counter=0;
    private final Node currentNode;
    private final int deepLevel;
    private final static int DEFAULT_DEEP_LEVEL=3;
    private final Set<Node> finalNodes;

    public Builder(
              final Node currentNode
              , final int deepLevel
              , final Set<Node> finalNodes
            ) {
        this.finalNodes=finalNodes;
        this.currentNode=currentNode;
        this.deepLevel=deepLevel;
    }


    public Builder(
            final Node currentNode
            , final int deepLevel
    ) {
        this(currentNode,deepLevel,new CopyOnWriteArraySet<>());
    }

    @Override
    public Set<Node> compute() {
        final List<ForkJoinTask<Set<Node>>> tasks=new ArrayList<>();
        final boolean hasBeenAdded=finalNodes.add(currentNode);
//        if(hasBeenAdded)
//            counter++;
        if (!currentNode.isFinal()) {
            final int arrLength=currentNode.getLength();
            for (int i = 0; i < arrLength; i++) {
                Node nextNode = currentNode.nextNode(i);
                if (nextNode == null || finalNodes.contains(nextNode))
                    continue;

                final Builder builder = new Builder(
                        nextNode
                        , deepLevel + 1
                        , finalNodes
                );
                if (isAsync()) {
                    tasks.add(builder.fork());
                } else {
                    builder.compute();
                }
            }
        }
        if (!tasks.isEmpty()){
            for (ForkJoinTask<Set<Node>> task:tasks) {
                task.join();
            }
        }
//        System.out.println(counter);
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
