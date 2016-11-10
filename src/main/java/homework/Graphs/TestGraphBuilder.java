package  homework.Graphs;

/**
 * Created by OTBA}|{HbIu` on 01.11.16.
 */
public class TestGraphBuilder {
    public static void main(String[] args) {
//        final GraphNode root=new GraphBuilder().build(XOField.Figure.X,new XOField());
        final GraphNode root=new GraphBuilderConcurentV0().build(XOField.Figure.X,new XOField(),0);

        System.out.println(root.getNode());
//        GraphHelper.show(root,0);
        System.out.println(GraphHelper.countNodes(root));
    }
}
