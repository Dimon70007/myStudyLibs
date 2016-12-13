package threadsstudy.swing_threads.swing_worker;

import javax.swing.*;
import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 23.11.16.
 */
public class SwingWorkerTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new SwingWorkerFrame();
                frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
