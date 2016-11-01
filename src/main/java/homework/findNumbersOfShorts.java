package main.java.homework;

import java.awt.*;
import java.util.Comparator;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

/**
 * Created by OTBA}|{HbIu` on 18.09.16.
 */
//На прямой даны N отрезков (в реальной жизни это могут быть промежутки
// времени, например), которые заданы координатами их левого и правого
// конца. Для каждого данного отрезка необходимо узнать, сколько из данных
// отрезков полностью находятся в нем. Один отрезок полностью содержится
// во втором, если левый конец первого отрезка находится правее левого
// конца второго отрезка, а правый конец первого находится левее правого
// конца второго. Предложите как можно более эффективный способ решения
// этой задачи. Гарантируется, что все концы данных отрезков различны.
public class findNumbersOfShorts {

    private static int numbersShorts=0;

    public static <P extends Point>  int findShorts(final List<? extends Point> list){
        NavigableSet<Point> intSet=new TreeSet<>(new Comparator<Point>(){

            @Override
            public int compare(Point o1, Point o2) {
                Point point1=(Point)o1;
                Point point2=(Point)o2;
                if(point1.getX()<point2.getX())
                    return -1;
                if(point1.getX()>point2.getX())
                    return 1;
                return 0;
            }
        });
        intSet.addAll(list);
        double lastY=intSet.last().getY();

        return looper(intSet,0);
    }

    private static int looper(NavigableSet<Point> points, int numbersShorts){

        if (points.size()==0)
            return numbersShorts;
        double lastY=points.pollLast().getY();
        for(Point point:points){
            if(point.getY() > lastY)
                numbersShorts++;
        }
        return 0;
    }
}
