package com.company.object;

import com.company.ScreenConverter;
import com.company.line.Line;
import com.company.line.LineDrawer;
import com.company.point.RealPoint;
import com.company.point.ScreenPoint;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DrawRectangle implements RectangleDrawer {
        private LineDrawer ld;
        private ScreenConverter sc;

    public DrawRectangle(LineDrawer ld, ScreenConverter sc) {
            this.ld = ld;
            this.sc = sc;
        }

//    @Override
//    public void drawRectangle(ScreenPoint p1, ScreenPoint p2, Color c) {
//
//        ScreenPoint p0 = new ScreenPoint(p2.getX(), p1.getY());
//        ScreenPoint p3 = new ScreenPoint(p1.getX(), p2.getY());
//
//        ld.drawLine(p1, p0);
//        ld.drawLine(p0, p2);
//        ld.drawLine(p2, p3);
//        ld.drawLine(p3, p1);
//
//
//        /*
//        p1-----p0
//        |       |
//        |       |
//        p3-----p2
//         */
//    }

    @Override
    public void drawRectangle(IFigure f, Color c) {
        List<RealPoint> realPoints = f.getMarkers();
        for (int i = 0; i < 3; i++) {
            ld.drawLine(sc.r2s(realPoints.get(i)), sc.r2s(realPoints.get(i + 1)), c);
        }
        ld.drawLine(sc.r2s(realPoints.get(3)), sc.r2s(realPoints.get(0)), c);
    }
}
