package hackerranck_challenges.staircase;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by OTBA}|{HbIu` on 09.04.17.
 */
public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        final int n = sc.nextInt();
        char[] arr = new char[n];
        Arrays.fill(arr,' ');
        String str = String.valueOf(arr);
        printN(n, str);
    }

    private static void printN(final int n, final String str) {
        if(n==0){
            return;
        }
        String newStr = str.substring(1) + "#";
        System.out.println(newStr);
        printN(n-1,newStr);
    }
}
