package com.company;

import javax.swing.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;

public class DrawPanel extends JPanel implements MouseMotionListener, MouseListener, MouseWheelListener {
    public DrawPanel() {
        this.addMouseMotionListener(this);
        this.addMouseListener(this);
        this.addMouseWheelListener(this);
    }
}
