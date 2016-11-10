package threadsstudy;

import javax.swing.*;
import java.awt.*;

/**
 * В этой программе осуществляется анимация скачущих мячей
 *
 */
public class Bounce {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame=new BounceFrame();
                frame.setTitle("BounceThread");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
