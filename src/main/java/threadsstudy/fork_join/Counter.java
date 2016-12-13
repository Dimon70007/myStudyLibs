package threadsstudy.fork_join;

import java.util.concurrent.RecursiveTask;
import java.util.function.DoublePredicate;

/**
 * Created by OTBA}|{HbIu` on 22.11.16.
 */
public class Counter extends RecursiveTask<Integer> {

    public static final int THRESHOLD=1000;
    private final double[] values;
    private final int from;
    private final int to;
    private final DoublePredicate filter;

    public Counter(double[] values, int from, int to, DoublePredicate filter) {
        this.values = values;
        this.from = from;
        this.to = to;
        this.filter = filter;
    }

    @Override
    protected Integer compute() {
        if (to-from<THRESHOLD){
            int count=0;
            for (int i=from;i<to;i++){
                if (filter.test(values[i])) count++;
            }
            return count;
        }
        int mid=(from +to)/2;
        Counter first=new Counter(values,from,mid,filter);
        Counter second=new Counter(values,mid,to,filter);
        invokeAll(first,second);
        return first.join()+second.join();
    }
}
