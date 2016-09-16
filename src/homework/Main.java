package homework;


import java.util.Random;

/**
 * Created by OTBA}|{HbIu` on 08.08.16.
 */
public class Main {


        public static void main(String[] args) {
            INode node=new ImmutableNode(0);
            Random r= new Random();
            for(int i=0;i<1000;i++){
                    node=node.add(r.nextInt()%500);
            }
            node.forEach(new Printer());
      //      System.out.printf("Count = %d", node.count());
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

