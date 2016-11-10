package  homework.Graphs;

import java.util.Set;

/**
 * Created by OTBA}|{HbIu` on 01.11.16.
 */
public class GraphNode {

    private final XOField node;

    private final Set<GraphNode> children;

    public GraphNode(final XOField node, Set<GraphNode> children) {
        this.node = node;
        this.children = children;
    }

    public XOField getNode() {
        return node;
    }

    public Set<GraphNode> getChildren() {
        return children;
    }
}
