package com.company.Object;

import com.company.LineDrawers.Line;
import com.company.LineDrawers.LineDrawer;
import com.company.Point.ScreenPoint;

import java.util.ArrayList;

public interface RectangleDrawer {
    void drawRectangle(ScreenPoint p1, ScreenPoint p2, LineDrawer ld);
    ArrayList<Line> mergeRectangle(Rectangle r1, Rectangle r2);
}
