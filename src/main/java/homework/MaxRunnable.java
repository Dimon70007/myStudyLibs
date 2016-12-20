package  homework;

import java.util.stream.IntStream;

/**
 * Created by OTBA}|{HbIu` on 21.10.16.
 */
public class MaxRunnable implements Runnable {

    private final int[] target;
    private Integer result;

    public MaxRunnable(final int[] target) {
        this.target=target;
        this.result=0;
    }

    @Override
    public void run() {
        result=IntStream.of(target).max().isPresent()?IntStream.of(target).max().getAsInt():0;
    }

    public int getResult() {
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static void main(String[] args) {
        MaxRunnable mr=new MaxRunnable(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        Thread t=new Thread(mr);
        t.start();
        System.out.println(mr.getResult());
        try {
            t.join();
            System.out.println(mr.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
