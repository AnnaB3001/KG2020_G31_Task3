package com.company.LineDrawers;

import com.company.Pixel.PixelDrawer;
import com.company.Point.ScreenPoint;

import java.awt.*;

public class BresenhamLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public BresenhamLineDrawer(PixelDrawer pixelDrawer) {
        this.pd = pixelDrawer;
    }

    @Override
    public void drawLine(ScreenPoint p1, ScreenPoint p2) {
        int x1 = p1.getX();
        int y1 = p1.getY();
        int x2 = p2.getX();
        int y2 = p2.getY();
        int x = x1;
        int y = y1;
        int absDx = Math.abs(x2 - x1);
        int absDy = Math.abs(y2 - y1);

        int dX = x2 - x1;
        int dY = y2 - y1;

        if (dY > 0) dY = 1;
        else dY = -1;

        if (dX > 0) dX = 1;
        else dX = -1;

        if (absDx >= absDy) {
            int error = 2 * absDy - absDx;
            for (int i = 0; i < absDx; i++) {
                pd.drawPixel(x, y, Color.BLACK);
                if (error >= 0) {
                    y += dY;
                    error += 2 * (absDy - absDx);
                } else error += 2 * absDy;
                x += dX;
            }
        } else {
            int error = 2 * absDx - absDy;
            for (int i = 0; i < absDy; i++) {
                pd.drawPixel(x, y, Color.BLACK);
                if (error >= 0) {
                    x += dX;
                    error += 2 * (absDx - absDy);
                } else error += 2 * absDx;
                y += dY;
            }
        }
    }
}
