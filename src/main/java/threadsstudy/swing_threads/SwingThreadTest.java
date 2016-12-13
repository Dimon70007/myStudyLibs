package threadsstudy.swing_threads;

import javax.swing.*;
import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 23.11.16.
 */
public class SwingThreadTest {

    public static void main(String[] args) {
        EventQueue.invokeLater(()->{
            JFrame frame=new SwingThreadFrame();
            frame.setTitle("SwingThreadTest");
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
