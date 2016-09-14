package mycollections;


import java.util.*;

/**
 * Created by OTBA}|{HbIu` on 08.08.16.
 */
public class Main {

        public static void main(String[] args){
                final List<Integer> testInstance = new ArrayList<>();
                for(int i=0;i<9;i++)
                        testInstance.add(i);
                testInstance.get(9);
                for(int i=testInstance.size();i!=0;i--) {
                        testInstance.remove(i);
                        System.out.println(testInstance.get(i));
                }
                final Integer[] input = new Integer[]{1,2,3};

                final Integer[] result = testInstance.toArray(input);
//        assertArrayEquals(input, result);
        }

        public void testRemoveWhenSizeEqualsLength() throws Exception {
                final Collection<Integer> testInstance = new ArrayList<>();
                for(int i=0;i<10;i++)
                        testInstance.add(i);
                for(int i=testInstance.size();i!=0;i--)
                        testInstance.remove(i);
                final Integer[] input = new Integer[]{1,2,3};

                final Integer[] result = testInstance.toArray(input);
//        assertArrayEquals(input, result);
        }
}

