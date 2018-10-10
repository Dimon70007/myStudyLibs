package hackerranck_challenges.current_buffer;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        final long count = in.nextLong();
        final Stream<Integer> input = Stream.generate(in::nextInt).limit(count);
        final float[] result = new float[3];
        Arrays.fill(result,0);
        input.forEach( (item ) -> {
            if(item > 0) {
                result[0]++;
            } else if(item < 0) {
                result[1]++;
            } else {
                result[2]++;
            }
        });
        in.close();
        for (float v : result) {
            System.out.printf("%.6f%n",v/count);
        }
    }
}