package dynamic_programing;

import com.sun.media.jfxmedia.logging.Logger;
import sun.rmi.runtime.Log;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Arrays.asList;

public class changeOnPurchase {

    private static Integer calcCountOfChange(List<Integer> nominals, Integer currentIdx, Integer[] results) {
        if (currentIdx < 0) return 0;
        if (currentIdx < 3) return 1;
        return nominals.stream().reduce(0, (acc, nominal) ->
                (currentIdx < nominal) ? acc : results[currentIdx - nominal] + acc);
    }

    public static int calcCountOfChange(Integer[] nominals, int changeSize) {
        if (changeSize < 0) return 0;
        List<Integer> usedNominals = Arrays.stream(nominals)
                .filter(nominal -> nominal <= changeSize).collect(Collectors.toList());
        if (changeSize < 2) return 1;
        int current = 0;
        Integer[] res = new Integer[changeSize+1];
        while(current <= changeSize) {
            res[current] = calcCountOfChange(usedNominals, current, res);
            current++;
        }
        return res[changeSize];
    }

    public static void main(String[] args) {
        Integer[] nominals = {1, 3, 5, 10};
        for (int i = 1; i < 20; i++) {
            int n = i;
            int result = calcCountOfChange(nominals, n);
            System.out.println("count of " + n + " with nominals " + Arrays.toString(nominals) + " = " + result);
        }
    }
}
