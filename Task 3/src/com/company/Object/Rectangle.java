package com.company.Object;

import com.company.Point.RealPoint;
import com.company.Point.ScreenPoint;
import com.company.ScreenConverter;

public class Rectangle {
    private RealPoint p1, p2; //координата начала, координата конца по диагонали
    private RealPoint p0, p3;

        /*
        p1-----p0
        |       |
        |       |
        p3-----p2
         */

    public Rectangle(RealPoint p1, RealPoint p2) {
        this.p1 = p1;
        this.p2 = p2;
        this.p0 = new RealPoint(p2.getX(), p1.getY());
        this.p3 = new RealPoint(p1.getX(), p2.getY());
    }

    public Rectangle(double x1, double y1, double x2, double y2){
        p1 = new RealPoint(x1, y1);
        p2 = new RealPoint(x2, y2);
        p0 = new RealPoint(x2, y1);
        p3 = new RealPoint(x1, y2);
    }


    public RealPoint getP1() {
        return p1;
    }

    public void setP1(RealPoint p1) {
        this.p1 = p1;
    }

    public RealPoint getP2() {
        return p2;
    }

    public void setP2(RealPoint p2) {
        this.p2 = p2;
        this.p0 = new RealPoint(p2.getX(), p1.getY());
        this.p3 = new RealPoint(p1.getX(), p2.getY());

    }

    public RealPoint getP0() {
        return p0;
    }

    public RealPoint getP3() {
        return p3;
    }

    public void transfer(ScreenPoint newPos, ScreenConverter sc){
        RealPoint currPos = sc.s2r(newPos);
        double currX = currPos.getX();
        double currY = currPos.getY();

        double width = Math.abs(p1.getX() - p2.getX());
        double height = Math.abs(p1.getY() - p2.getY());

        this.p1 = new RealPoint(currX - width / 2, currY + height / 2);
        this.p2 = new RealPoint(currX + width / 2, currY - height / 2);
        this.p3 = new RealPoint(p1.getX(), p2.getY());
        this.p0 = new RealPoint(p2.getX(), p1.getY());
    }

    public void scale(ScreenPoint lastPosition, RealPoint newPosition, ScreenConverter sc){
        RealPoint prevPos = sc.s2r(lastPosition);
        double prevX = prevPos.getX();
        double prevY = prevPos.getY();

        RealPoint currPos = newPosition;
        double currX = currPos.getX();
        double currY = currPos.getY();

        double width = Math.abs(p2.getX() - p1.getX());
        double height = Math.abs(p2.getY() - p1.getY());




        if(Math.abs(p1.getX() - prevX) <= (width / 2) && Math.abs(p1.getY() - prevY) <= (height / 2)){
            this.p1 = new RealPoint(p1.getX() + currX, p1.getY() + currY);
            this.p0 = new RealPoint(p2.getX(), p1.getY());
            this.p3 = new RealPoint(p1.getX(), p2.getY());
        } else if(Math.abs(p2.getX() - prevX) <= (width / 2) && Math.abs(p2.getY() - prevY) <= (height / 2)){
            this.p2 = new RealPoint(p2.getX() + currX, p2.getY() + currY);
            this.p0 = new RealPoint(p2.getX(), p1.getY());
            this.p3 = new RealPoint(p1.getX(), p2.getY());
        } else if (Math.abs(p0.getX() - prevX) <= (width / 2) && Math.abs(p0.getY() - prevY) <= (height / 2) ){
            this.p0 = new RealPoint(p0.getX() + currX, p0.getY() + currY);
            this.p1 = new RealPoint(p3.getX(), p0.getY());
            this.p2 = new RealPoint(p0.getX(), p3.getY());
        } else if (Math.abs(p3.getX() - prevX) <= (width / 2) && Math.abs(p3.getY() - prevY) <= (height / 2)
        ){
            this.p3 = new RealPoint(p3.getX() + currX, p3.getY() + currY);
            this.p1 = new RealPoint(p3.getX(), p0.getY());
            this.p2 = new RealPoint(p0.getX(), p3.getY());
        }




    }

    public boolean checkClick(ScreenPoint dot, ScreenConverter sc){
        RealPoint dotReal = sc.s2r(dot);
        double x = dotReal.getX();
        double y = dotReal.getY();
        double width = Math.abs(p1.getX() - p2.getX());
        double height = Math.abs(p1.getY() - p2.getY());

        return Math.abs(p1.getX() - x) < width && Math.abs(p1.getY() - y) < height;

    }
}

