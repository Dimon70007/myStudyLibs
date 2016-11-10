package threadsstudy;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by OTBA}|{HbIu` on 09.11.16.
 * the frame with panel and buttons
 */
public class BounceFrame extends JFrame {
    private BallComponent comp;

    /**
    * Конструирует фрейм с компонентом, отображающим скачущий мяч, а также кнопки
    * Start и Close
    */
    public BounceFrame(){
//        setTitle("Bounce");
        comp=new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel=new JPanel();
        addButton(buttonPanel,"Start",new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                addBall();
            }
        });
        addButton(buttonPanel,"Close",new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        add(buttonPanel,BorderLayout.SOUTH);
        pack();
    }
    /**
     *Вводит кнопку в контейнер
     * @param c is Conteiner
     * @param title is text on the button
     * @param listener is button's action handler
     */
    public void addButton(Container c,String title,ActionListener listener){
        JButton button=new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    /**
     * Inserts jumping ball on panel and presents 1000 jumps
     */
    public void addBall() {
        Ball ball = new Ball();
        comp.add(ball);
        Runnable r=new BallRunnable(ball,comp);
        new Thread(r).start();
    }
}
