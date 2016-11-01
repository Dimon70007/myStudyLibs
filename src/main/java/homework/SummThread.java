package main.java.homework;

import java.util.stream.IntStream;

/**
 * Created by OTBA}|{HbIu` on 21.10.16.
 */

// BEGIN (write your solution here)
class SummThread extends Thread{
    int[] toSum;
    int result;
    public SummThread(final int[] toSum){
        this.toSum=toSum;
        this.result=0;
    }

    @Override
    public void run(){
        result = IntStream.of(toSum).sum();

//        for(int i:toSum){
//            result+=i;
//        }
    }

    public int getResult(){
        return result;
    }

    public static void main(String[] args) {
        SummThread st=new SummThread(new int[]{1,2,3,4,5,6,7,8,9});
        st.start();
        System.out.println(st.getResult());
        try {
            st.join();
            System.out.println(st.getResult());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
