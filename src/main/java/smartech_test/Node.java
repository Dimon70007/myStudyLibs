package smartech_test;

import java.util.*;

/**
 * Created by OTBA}|{HbIu` on 08.02.17.
 */
public class Node implements Iterable<Node>{

    private final int[] arr;
    private final int arrLength;
    private final int colorsCount;
    private final int color;
    private final Set<Node> children;
    private final Node parentNode;

    public Node(final Node parentNode
            , final int [] arr
            , final int colorsCount
            ,final int color) {
        this.parentNode=parentNode;
        this.arr=arr;
        this.colorsCount=colorsCount;
        this.color = color;
        this.children=new HashSet<>();
        arrLength = this.arr.length;
    }

    public Node(final int [][] arr, final int colorsCount) {
        this.arr= NodeHelper
                .convertMatrixToVector(arr);
        this.color =0;
        this.colorsCount=colorsCount;
        this.parentNode=null;
        this.children=new HashSet<>();
        arrLength = this.arr.length;
    }

    public int getColor() {
        return color;
    }

    public int getColorsCount() {
        return colorsCount;
    }

    public boolean hasChildren(){
        return !this.children.isEmpty();
    }

    public Set<Node> getChildren() {
        return children;
    }

    public synchronized Set<Node> createChildren(final int nextColor) {
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
            //короче размер поля 32x32 > количества цветов 256
            this.children.addAll(getNextNodes(0,nextColor,new HashSet<>()));
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
        return arr[arrLength-1]==colorsCount-1;
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
    private Set<Node> getNextNodes(final int currentCoordinate,final int nextColor,final Set<Node> acc){
        if (arrLength==currentCoordinate) {
            return acc;
        }
        final int nextCoordinate= currentCoordinate +1;
        //currentCoordinate
        int[] nextArr=Arrays.copyOf(arr,arr.length);
        //внимание следим за руками)))
        //здесь мы генерируем children с текущим цветом,
        // а color туда передаем со следующим, получившим снаружи - nextColor
        nextArr[currentCoordinate]=color;
        final Node nextNode=new Node(this,nextArr,colorsCount,nextColor);
        acc.add(nextNode);
        return getNextNodes(nextCoordinate,nextColor,acc);
    }

    @Override
    public Iterator<Node> iterator() {

        return new NodeIterator();
    }

    private class NodeIterator implements Iterator<Node>{
        private int deepLevel;
        Node next;        // next entry to return
        Node current;     // current entry
        Iterator<Node> iterator;
        Deque<Iterator<Node>> parentIterators;


        NodeIterator() {
            current = next = null;
            next=Node.this;
            iterator=children.iterator();
            parentIterators=new ArrayDeque<>();
            this.deepLevel=0;
        }

        public final boolean hasNext() {
            return iterator.hasNext();
        }

        public final Node next() {
            if (next==null)
                throw new NoSuchElementException();

            if (iterator.hasNext()){//iter on children
                current=next;
                Node childNode=iterator.next();
                if(childNode.iterator().hasNext()){
                    System.out.println("children.size()="+children.size());
                    deepLevel=parentIterators.size();
                    if(deepLevel>0 && deepLevel%100000==0)
                            System.out.println("deeplevel="+deepLevel);

                    parentIterators.push(iterator);
                    iterator=childNode.iterator();//go down in 1 level lower
                } else{
                    next=childNode;
                }
            } else{
                try {
                    iterator=parentIterators.pop();
//                    deepLevel--;
                }catch (NoSuchElementException e){
                    iterator=null;
                }
                next=iterator!=null? iterator.next(): null;
            }


            return current;


        }

    }
}
