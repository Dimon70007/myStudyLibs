package threadsstudy.swing_threads;

import javax.swing.*;
import java.util.Random;

/**
 * Created by OTBA}|{HbIu` on 23.11.16.
 */
public class BadWorkerRunnable implements Runnable {

    private final JComboBox<Integer> comboBox;
    private final Random generator;

    public BadWorkerRunnable(JComboBox<Integer> comboBox) {

        generator = new Random();
        this.comboBox = comboBox;
    }

    @Override
    public void run() {

        try {
            while (true){
                int i=Math.abs(generator.nextInt());
                if (i%2==0){
                    comboBox.insertItemAt(i,0);
                }else if (comboBox.getItemCount()>0){
                    comboBox.removeItemAt(i%comboBox.getItemCount());
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }

    }
}
