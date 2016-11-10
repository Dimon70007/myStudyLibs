package threadsstudy;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * Анимация движения и отскока мяча от краев прямоугольника
 */
public class Ball {
    public static final int XSIZE=15;
    public static final int YSIZE=15;
    private double x=400;
    private double y=15;
    private double dx=1;
    private double dy=1;

    /*
    * Moves the ball to the next position, changing the direction of moving,
    * when it is at the end of rectangle
    */
    public void move(Rectangle2D bounds) {
        x+=dx;
        y+=dy;
        if (x<bounds.getMinX()){
            x=bounds.getMinX();
            dx=-dx;
        }
        if (x+XSIZE>=bounds.getMaxX()){
            x=bounds.getMaxX()-XSIZE;
            dx=-dx;
        }
        if (y<bounds.getMinY()){
            y=bounds.getMinY();
            dy=-dy;
        }

        if (y+YSIZE>=bounds.getMaxY()){
            y=bounds.getMaxY()-YSIZE;
            dy=-dy;
        }
    }

    /*
    * Getting balls form in its current position
    */
    public Ellipse2D getShape(){
      return new Ellipse2D.Double(x,y,XSIZE,YSIZE);
    }

}
