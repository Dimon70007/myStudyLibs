package hackerranck_challenges.min_max_sum;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        long a = in.nextLong();
        long b = in.nextLong();
        long c = in.nextLong();
        long d = in.nextLong();
        long e = in.nextLong();

        List<Long> list = Arrays.asList(a, b, c, d, e);
        list.sort((x, y) -> {
            Long result = x - y;
            return result == 0L ? 0 : (int) (result / Math.abs(result));
        });
        final long max = list.stream().skip(1).reduce(0L, (acc, item) -> acc + item);
        final long min = list.stream().limit(list.size()-1).reduce(0L, (acc, item) -> acc + item);
        System.out.printf("%d %d", min, max);
    }
}