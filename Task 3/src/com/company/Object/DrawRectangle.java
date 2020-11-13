package com.company.Object;

import com.company.LineDrawers.Line;
import com.company.LineDrawers.LineDrawer;
import com.company.Point.RealPoint;
import com.company.Point.ScreenPoint;

import java.util.ArrayList;

public class DrawRectangle implements RectangleDrawer {



    @Override
    public void drawRectangle(ScreenPoint p1, ScreenPoint p2, LineDrawer ld) {

        ScreenPoint p0 = new ScreenPoint(p2.getX(), p1.getY());
        ScreenPoint p3 = new ScreenPoint(p1.getX(), p2.getY());

        ld.drawLine(p1, p0);
        ld.drawLine(p0, p2);
        ld.drawLine(p2, p3);
        ld.drawLine(p3, p1);


        /*
        p1-----p0
        |       |
        |       |
        p3-----p2
         */
    }

    private ArrayList<RealPoint> pointsOfNewPolygon(Rectangle r1, Rectangle r2){
        ArrayList<RealPoint> points = new ArrayList<>();



        if (r1.getP1().getX() < r2.getP1().getX()){
            points.add(r1.getP1()); //0
            points.add(new RealPoint(r2.getP1().getX(), r1.getP1().getY())); //1
            points.add(r2.getP1()); //2
            points.add(r2.getP0()); //3
            if (r1.getP0().getX() > r2.getP0().getX()){ //r2 внутри r1
                points.add(new RealPoint(r2.getP0().getX(), r1.getP1().getY())); //4
                points.add(r1.getP0()); //5
                points.add(r1.getP2()); //6
                points.add(r1.getP3()); //7
            } else { //r2 выходит за правую грань r1
                points.add(r2.getP2()); //4
                if(r1.getP2().getY() < r2.getP2().getY()){ //r2 выходит за правый угол r1
                    points.add(new RealPoint(r1.getP2().getX(), r2.getP2().getY())); //5
                    points.add(r1.getP2()); //6
                }else{ //r2 выходит за правую грань r1
                    points.add(r2.getP3());
                    points.add(new RealPoint(r2.getP3().getX(), r1.getP3().getY()));//6
                }
                points.add(r1.getP3()); //7
            }
        } else {

        }

        return points;
    }

    @Override
    public ArrayList<Line> mergeRectangle(Rectangle r1, Rectangle r2){
        ArrayList<RealPoint> points = pointsOfNewPolygon(r1, r2);
        ArrayList<Line> polygon = new ArrayList<>();

        for (int i = 0; i < points.size() - 1; i++) {
            polygon.add(new Line(points.get(i), points.get(i+1)));
        }
        polygon.add(new Line(points.get(0), points.get(points.size() - 1)));

        return polygon;
    }





    private boolean isCrossed(Rectangle r1, Rectangle r2){

        return !(r1.getP1().getX() > r2.getP2().getX()) && !(r1.getP2().getX() < r2.getP1().getX())
                && !(r1.getP1().getY() > r2.getP2().getY()) && !(r1.getP2().getY() < r2.getP1().getY());
    }
}
