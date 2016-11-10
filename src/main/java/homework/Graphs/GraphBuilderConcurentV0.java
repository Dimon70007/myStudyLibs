package  homework.Graphs;

import java.util.*;

/**
 * Created by OTBA}|{HbIu` on 01.11.16.
 */
public class GraphBuilderConcurentV0 {
    public GraphNode build(XOField.Figure currentFigure,
                           XOField currentField,
                           final int deepLevel) {
        if(deepLevel>3) return new GraphNode(currentField, Collections.emptySet());
        final List<Thread> threads=new ArrayList<>();
        final XOField.Figure nextFigure
                =currentFigure==XOField.Figure.O ? XOField.Figure.X: XOField.Figure.O;
        final Set<GraphNode> children=new HashSet<>();
        for (int y=0;y<3;y++) {
            for (int x = 0; x < 3; x++) {
                if (currentField.getFigure(x,y)!=null)
                    continue;
                final XOField newField=new XOField(currentField);
                newField.setFigure(x,y,nextFigure);
                //рекурсивный вызов
                final Thread threadThatAddsChildren=new Thread(new Runnable() {
                    @Override
                    public void run() {

                        children.add(build(nextFigure,newField,deepLevel+1));
                    }
                });
                threadThatAddsChildren.start();
                threads.add(threadThatAddsChildren);
            }
        }
        for (Thread t:threads){
            try {
                t.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return new GraphNode(currentField,children);
    }
}
