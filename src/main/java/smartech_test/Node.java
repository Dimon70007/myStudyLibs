package smartech_test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by OTBA}|{HbIu` on 08.02.17.
 */
public class Node { //implements Iterable<Node>{

    private final int[] arr;
    private final int colorsCount;
    private final int coordinate;
    private final Set<Node> children;
    private final Node parentNode;

    public Node(final Node parentNode
            , final int [] arr
            , final int colorsCount
            ,final int coordinate) {
        this.parentNode=parentNode;
        this.arr=arr;
        this.colorsCount=colorsCount;
        this.coordinate=coordinate;
        this.children=new HashSet<>();
    }

    public Node(final int [][] arr, final int colorsCount) {
        this.arr= NodeHelper
                .convertMatrixToVector(arr);
        this.coordinate=0;
        this.colorsCount=colorsCount;
        this.parentNode=null;
        this.children=new HashSet<>();
    }

    public int getCoordinate() {
        return coordinate;
    }

    public boolean hasChildren(){
        return !this.children.isEmpty();
    }

    public Set<Node> getChildren() {
        return children;
    }

    public synchronized Set<Node> createChildren(final int nextCoordinate) {
        if(!hasChildren()){
        //если мы все равно сначала генерируем все возможные состояния для первой ячейки
            //то для второй - нужно начинать генерацию...со второй, но что делать с первой...
            //какое значение в ней оставлять - скорее всего там будет максимальное значение
            // (как выяснилось позже решение - это дерево состояний, то есть у нас есть лес потомков,
            // у которых есть лес потомков, у которых... при этом с каждым уходом в глубину индекс
            // ячейк сдвигается на 1 и в конце мы получаем самое малое количество состояний, зависящее
            // от количества цветов)
            //и этот элемент трогать не надо, т.е. если на вход поступила координата 5 то мы вызываем
            //некст стэйт у координат больше 5 - эта логика должна быть в билдере
            //              0  0  0
            //       V         V          V
            //  0  0  1     0  1  0    1  0  0
            //     V   V
//        1  0  1    0  1  1
//           V           V
            // 1  1  1      1  1  1

            //    0  0
            //     V           V
            //1  0   2  0    0  1   0  2
            //  V
            //1  1   1  2
            //в общем будем генерировать множество потомков по одной координате с разными состояниями координаты
            // и потом у этих потомков брать следующую координату и генерить дальше, пока не окажется что
            //последняя координата = colorsCount-1
            this.children.addAll(getNextNodes(0,nextCoordinate,new HashSet<>()));
        }
        return this.children;
    }

    public Node getParentNode() {
        return parentNode;
    }

    public int[] getArr() {
        return arr;
    }

    public int[][] get2DArr(final int sizeX) {
        return NodeHelper.convertVetorToMatrix(arr,sizeX);
    }

    public boolean isFinal(){
        return arr[arr.length-1]==colorsCount-1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return Arrays.equals(arr, node.arr);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(arr);
    }

    /*
    создаем множество нод изменяя значение ячейки с текущей координатой,
    а сохраняем в ноды-потомки следующую координату, полученную параметром
    * */
    private Set<Node> getNextNodes(final int currentColor,final int nextCoordinate,final Set<Node> acc){
        if (currentColor==this.colorsCount) {
            return acc;
        }
        final int nextColor=currentColor+1;
        //currentCoordinate
        arr[coordinate]=currentColor;
        final Node nextNode=new Node(this,Arrays.copyOf(arr,arr.length),colorsCount,nextCoordinate);
        acc.add(nextNode);
        return getNextNodes(nextColor,nextCoordinate,acc);
    }
//
//    @Override
//    public Iterator<Node> iterator() {
//
//        return new NodeIterator<>();
//    }
//
//    private class NodeIterator implements Iterator<Node>{
//        Node next;        // next entry to return
//        Node current;     // current entry
//        int index;             // current slot
//        int size;
//        Iterator<Node> chIterator=children.iterator();
//
//        NodeIterator() {
//            Set<Node> t=children;
//            current = next = null;
//            next=chIterator.hasNext()?chIterator.next():null;
//        }
//
//        public final boolean hasNext() {
//            return next != null;
//        }
//
//        final Node next() {
//            Iterator<Node> deepIterator=next.children.iterator();
//            current=deepIterator.hasNext()
//                    ?deepIterator.next():chIterator.next();
//
//            next=chIterator.hasNext()?
//                    chIterator.next().iterator().next()
//                    :null;
//            TreeSet
//
//
//        }
//
//    }
}
