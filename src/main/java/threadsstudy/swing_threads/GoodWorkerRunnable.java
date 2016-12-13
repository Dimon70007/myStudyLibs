package threadsstudy.swing_threads;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by OTBA}|{HbIu` on 23.11.16.
 */
public class GoodWorkerRunnable implements Runnable {

    private final JComboBox<Integer> comboBox;
    private final Random generator;
    public GoodWorkerRunnable(JComboBox<Integer> comboBox) {

        this.comboBox = comboBox;
        generator = new Random();
    }

    @Override
    public void run() {
        try {
            while (true){
                EventQueue.invokeLater(() -> {
                    int i=Math.abs(generator.nextInt());
                    if (i%2==0){
                        comboBox.insertItemAt(i,0);
                    }else if (comboBox.getItemCount()>0){
                        comboBox.removeItemAt(i%comboBox.getItemCount());
                    }
                });
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
//            e.printStackTrace();
        }
    }
}
