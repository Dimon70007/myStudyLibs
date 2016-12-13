package smarteck_resulting_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OTBA}|{HbIu` on 13.12.16.
 */
public class FieldGenerator {
    public static void main(String[] args) {
        int xLength=3;
        int yLength=3;
        int colorLimit=256;
        INode<Integer[][]> node=new Node(null,xLength,yLength,colorLimit);
        List<INode<Integer[][]>> nodesList=new ArrayList<>();
        while(node!=null){
            nodesList.add(node);
            System.out.println(node);
            node=node.getNextNode();
        }
    }
}
