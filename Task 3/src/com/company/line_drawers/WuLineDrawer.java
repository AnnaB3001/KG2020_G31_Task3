package com.company.line_drawers;

import com.company.LineDrawer;
import com.company.Pixel.PixelDrawer;

import java.awt.*;

public class WuLineDrawer implements LineDrawer {
    private PixelDrawer pd;

    public WuLineDrawer(PixelDrawer pd) {
        this.pd = pd;
    }

    @Override
    public void drawLine(int x1, int y1, int x2, int y2) {
        int x, y, dx, dy;
        int absY = Math.abs(y2 - y1);
        int absX = Math.abs(x2 - x1);
        boolean s = false;

        if (absY < absX) {
            if (x1 < x2) {
                x = x1;
                y = y1;
                dx = x2 - x1;
                dy = y2 - y1;
            } else {
                x = x2;
                y = y2;
                dx = x1 - x2;
                dy = y1 - y2;
            }
        } else {
            s = true;
            if (y1 < y2) {
                x = y1;
                y = x1;
                dx = y2 - y1;
                dy = x2 - x1;
            } else {
                x = y2;
                y = x2;
                dx = y1 - y2;
                dy = x1 - x2;
            }
        }

        int error = 0;
        for (int i = 0; i <= dx; i++) {
            drawWuPixel(x, y, error, dx, s);
            error += 2 * dy;
            if (error > dx) {
                error -= 2 * dx;
                y++;
            } else if (error < -dx) {
                error += 2 * dx;
                y--;
            }
            x++;
        }
    }

    private void drawWuPixel(int x, int y, int error, int dx, boolean swap) {
        Color c = new Color(15, 50, 158);
        Color c1, c2;

        int d = dx != 0 ? (255 * error) / (2 * dx) : 255;
        int dPos = Math.max(0, d);
        c1 = setColor(255 - Math.abs(d), c);
        c2 = setColor(Math.abs(d), c);

        if (!swap) {
            pd.drawPixel(x, y, c1);
            if (dx != 0) {
                if (dPos > 0)
                    pd.drawPixel(x, y + 1, c2);
                else
                    pd.drawPixel(x, y - 1, c2);
            }
        } else {
            pd.drawPixel(y, x, c1);
            if (dx != 0) {
                if (dPos > 0)
                    pd.drawPixel(y + 1, x, c2);
                else
                    pd.drawPixel(y - 1, x, c2);
            }
        }
    }

    private Color setColor(int t, Color c) {
        return new Color(c.getRed(), c.getGreen(), c.getBlue(), t);
    }
}
