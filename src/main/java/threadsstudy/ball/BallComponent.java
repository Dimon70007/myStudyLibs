package threadsstudy.ball;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OTBA}|{HbIu` on 09.11.16.
 * Component that paints the ball
 */
public class BallComponent extends JPanel{

    public static final int DEFAULT_WIDTH=450;
    public static final int DEFAULT_HEIGHT=450;

    private List<Ball> balls=new ArrayList<>();
    /**
    * Insert the ball in component
    * @param ball inserting ball
    */
    public void add(Ball ball) {
        balls.add(ball);
    }

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);//cls
        Graphics2D g2=(Graphics2D)g;
        for (Ball b:balls){
            g2.fill(b.getShape());

        }
    }
    @Override
    public Dimension getPreferredSize(){
        return new Dimension(DEFAULT_WIDTH,DEFAULT_HEIGHT);
    }

}
