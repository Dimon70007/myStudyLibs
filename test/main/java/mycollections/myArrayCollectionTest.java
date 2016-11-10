package  mycollections;

import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * тесты писать умею, но эти не мои
 */
public class myArrayCollectionTest {

    @Test
    public void testToArrayWhenInputArrayHaveSizeOne() throws Exception {
        final Collection<Integer> testInstance = new myArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[10];

        final Integer[] result =  testInstance.toArray(input);
        assertNotEquals(input, result);
        assertEquals((Integer)1, result[0]);
        assertEquals((Integer)2, result[1]);
        assertEquals((Integer)3, result[2]);
        assertEquals(3, result.length);
    }

    @Test
    public void testToArrayWhenInputArrayHaveCorrectSize() throws Exception {
        final Collection<Integer> testInstance = new myArrayCollection<>();
        testInstance.add(1);
        testInstance.add(2);
        testInstance.add(3);

        final Integer[] input = new Integer[]{1,2,3};

        final Integer[] result = testInstance.toArray(input);
        assertArrayEquals(input, result);
    }

    @Test
    public void testRemoveWhenSizeEqualsLength() throws Exception {
        final Collection<Integer> testInstance = new myArrayCollection<>();
        for(int i=0;i<10;i++)
            testInstance.add(i);
        for(Integer i:testInstance)
            testInstance.remove(i);
        final Integer[] input = new Integer[]{1,2,3};

        final Integer[] result = testInstance.toArray(input);
//        assertArrayEquals(input, result);
    }

}