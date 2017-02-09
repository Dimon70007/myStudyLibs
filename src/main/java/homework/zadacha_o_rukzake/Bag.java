package homework.zadacha_o_rukzake;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by OTBA}|{HbIu` on 26.12.16.
 */
public class Bag {
    private final int[] clothes;
    private int currentIndex;
    private final Deque<Integer> result;
    private final int bagsWeight;

    public Bag(final int[] clothes, final int bagsWeght) {
        this.clothes = clothes;
        result=new ArrayDeque<>();
        bagsWeight = bagsWeght;
        currentIndex=0;
    }

    public Integer[] getResultList() {
        for (int i = 0; i < clothes.length; i++) {
            if( calculate(bagsWeight,0,i)==bagsWeight)
                return (Integer[]) result.toArray();
        }
        return null;
    }

    private int calculate(int targetWeight, int initIndex, int currInd){

        int tmp=clothes[initIndex];
        if (initIndex==currInd)
            return calculate(targetWeight-tmp,initIndex+1,currInd);

        if (targetWeight<0 || initIndex>=clothes.length) {
            int i=result.poll();
            return 0;
        }

        if (targetWeight==0)
            return bagsWeight;

        result.push(tmp);

        return calculate(targetWeight-tmp,initIndex+1,currInd);

    }

    public static void main(String[] args) {
        Bag bag=new Bag(new int[]{12,5,7,8},20);

    }
}
