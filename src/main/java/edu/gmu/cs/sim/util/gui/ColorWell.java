/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.util.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ColorWell extends JPanel {
    Color color;

    public ColorWell() {
        this(new Color(0, 0, 0, 0));
    }

    public ColorWell(Color c) {
        color = c;
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                Color col = JColorChooser.showDialog(null, "Choose Color", getBackground());
                setColor(col);
            }
        });
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
    }

    // maybe in the future we'll add an opacity mechanism
    public void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void setColor(Color c) {
        if (c != null) {
            color = changeColor(c);
        }
        repaint();
    }

    public Color getColor() {
        return color;
    }

    public Color changeColor(Color c) {
        return c;
    }
}
