package threadsstudy.fork_join;


import java.util.concurrent.ForkJoinPool;

/**
 * Created by OTBA}|{HbIu` on 22.11.16.
 */
public class ForkJoinTest {

    public static int startTest() {
        final int SIZE=10000000;
        int count=10000;
        final double number=0.5;
        double[] numbers=new double[SIZE];
        for (int i=0;i<SIZE;i++){
            double tmp=Math.random();
            if (count>0 && tmp!=number){
                numbers[i]=number;
                count--;
                continue;
            }
            numbers[i]=tmp;
        }

        Counter counter=new Counter(numbers,0,numbers.length,x-> x==0.5);
        ForkJoinPool pool=new ForkJoinPool();
        pool.invoke(counter);
        int result=counter.join();
        if (result!=10000)
            System.out.println(result);
        return result;
    }

    public static void main(String[] args) {
        int testsCount=100;
        for (int i=0;i<testsCount;i++){
            startTest();
        }
    }
}
