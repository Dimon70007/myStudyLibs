package main.java.homework;

import org.junit.Test;

import java.util.Random;

/**
 * Created by OTBA}|{HbIu` on 16.09.16.
 */
public class ImmutableNodeTest {

    @Test
    public void addNodesTest() throws Exception {
        INode node=new ImmutableNode(0);
        Random r= new Random();
        int tmp;
        for(int i=0;i<1000;i++){
            node=node.add( r.nextInt()%500);
        }
        forEachTest(node);
        countTestWithoutCount(node);

    }

    public void forEachTest(INode node) throws Exception {

        node.forEach(new Printer());
    }

    public void countTestWithoutCount(INode node) throws Exception {

        Counter counter= new Counter();
        node.forEach(counter);
        System.out.printf("Counts of INodes = %d",counter.getCount());
    }

    private static class Printer implements INode.Consumer<Integer>{
        @Override
        public void consume(final Integer value) {
            if(value%5==0) System.out.println(value);
        }


    }

    private static class Counter implements INode.Consumer<Integer>{
        private int count=0;

        @Override
        public void consume(Integer value) {
            count++;
        }
        public int getCount() {
            return count;
        }
    }

}