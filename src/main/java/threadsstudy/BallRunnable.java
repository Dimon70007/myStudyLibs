package threadsstudy;

import java.awt.*;

/**
 * Created by OTBA}|{HbIu` on 09.11.16.
 */
public class BallRunnable implements Runnable {

    private final Ball ball;
    private Component comp;
    public static final int STEPS=1000;
    public static final int DELAY=5;
    /**
     * Constructs an object for executing thread
     * @param ball is jumping ball
     * @param comp is comp, that has a ball
     */
    public BallRunnable(Ball ball, Component comp) {
        this.ball=ball;
        this.comp =comp;
    }

    @Override
    public void run() {
        try {
            for (int i=1;i<=STEPS;i++){
                ball.move(comp.getBounds());
                comp.paint(comp.getGraphics());
                Thread.sleep(DELAY);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
