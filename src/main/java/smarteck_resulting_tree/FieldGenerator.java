package smarteck_resulting_tree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by OTBA}|{HbIu` on 13.12.16.
 */
public class FieldGenerator {
    //последовательно генерирует граф состояний (вариантов расцветки) и сохраняет их в список
    //как вариант можно реализовать компаратор и закидывать в set чтобы при вращении не было повторений
    //написал сам... в учебник не подсматривал. наверно в спокойной обстановке лучше думается
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
