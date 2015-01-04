/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.app.mousetraps;

import edu.gmu.cs.sim.engine.SimState;
import edu.gmu.cs.sim.engine.Steppable;

public class MouseTrap implements Steppable {
    private static final long serialVersionUID = 1;

    public int posx, posy;

    public MouseTrap(int x, int y) {
        this.posx = x;
        this.posy = y;
    }

    public void step(final SimState state) {
        ((MouseTraps) state).triggerTrap(posx, posy);
    }
}
