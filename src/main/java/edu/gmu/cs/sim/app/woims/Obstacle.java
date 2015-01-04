/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.app.woims;

import java.awt.*;

import edu.gmu.cs.sim.portrayal.simple.OvalPortrayal2D;

public class Obstacle extends OvalPortrayal2D {
    private static final long serialVersionUID = 1;

    public final static Paint obstacleColor = new Color(192, 255, 192);
    // gradient obstacles!  Try it!  Slower but fun!
    // public final static Paint obstacleColor = new GradientPaint(0,0,Color.red,10,10,Color.green,true);

    public Obstacle(double diam) {
        super(obstacleColor, diam);
    }
}
