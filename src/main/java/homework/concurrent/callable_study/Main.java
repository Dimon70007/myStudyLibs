package homework.concurrent.callable_study;
import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Callable;

public class Main {

    public static void main(String... args) throws Exception {
        testArray(new Integer[0], 0);
        testArray(new Integer[]{1, 4, 10, 2}, 10);
        testArray(new Integer[]{1, 4, -10, 2}, 4);
        testArray(new Integer[]{1}, 1);

    }

    private static void testArray(final Integer[] inputArray, final int expectedResult) {
        final int actualResult = new MaxFinder(inputArray).call();
        if (actualResult != expectedResult) {
            throw new RuntimeException(
                    String.format(
                            "Actual max: %d, expected max: %d",
                            actualResult,
                            expectedResult));
        }
    }

    // BEGIN (write your solution here)
    private static class MaxFinder implements Callable<Integer>{
        final Integer[] array;

        MaxFinder(final Integer[] array){
            this.array=array;
        }

        public Integer call(){
            if (array.length==0)
                return 0;
            return Arrays.stream(array).max(Comparator.naturalOrder()).get();
        }

    }
    // END

}
