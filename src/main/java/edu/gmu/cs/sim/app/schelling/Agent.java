/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.app.schelling;

import edu.gmu.cs.sim.engine.SimState;
import edu.gmu.cs.sim.engine.Steppable;
import edu.gmu.cs.sim.util.Int2D;
import edu.gmu.cs.sim.util.IntBag;

public /*strictfp*/ class Agent implements Steppable {
    private static final long serialVersionUID = 1;

    Int2D loc;
    IntBag neighborsX = new IntBag(9);
    IntBag neighborsY = new IntBag(9);

    public Agent(int x, int y) {
        loc = new Int2D(x, y);
    }

    public void step(final SimState state) {
        Schelling sch = (Schelling) state;
        int[][] locs = sch.neighbors.field;
        int x = loc.x;
        int y = loc.y;

        if (locs[x][y] < Schelling.RED) {
            return;  // not an agent
        }
        if (sch.emptySpaces.numObjs == 0) {
            return;  // nowhere to move to!
        }

        // get all the places I can go.  This will be slow as we have to rely on grabbing neighbors.
        sch.neighbors.getMooreLocations(loc.x, loc.y, sch.neighborhood, sch.neighbors.BOUNDED, true, neighborsX, neighborsY);

        // compute value
        double val = 0;
        int threshold = sch.threshold;  // locals a little faster
        int numObjs = neighborsX.numObjs;
        int[] objsX = neighborsX.objs;
        int[] objsY = neighborsY.objs;
        int myVal = locs[x][y];

        for (int i = 0; i < numObjs; i++) {
            if (locs[objsX[i]][objsY[i]] == myVal // just like me
                && !(objsX[i] == x && objsY[i] == y))  // but it's NOT me
            {
                val += 1.0 / Math.sqrt((x - objsX[i]) * (x - objsX[i]) + (y - objsY[i]) * (y - objsY[i]));
                if (val >= threshold) {
                    return;  // we're not moving
                }
            }
        }

        // find a new spot to live -- a random jump? Move to a nearby location?  Websites differ
        int newLocIndex = state.random.nextInt(sch.emptySpaces.numObjs);
        Int2D newLoc = (Int2D) (sch.emptySpaces.objs[newLocIndex]);
        sch.emptySpaces.objs[newLocIndex] = loc;

        // swap colors
        int swap = locs[newLoc.x][newLoc.y];
        locs[newLoc.x][newLoc.y] = locs[loc.x][loc.y];
        locs[loc.x][loc.y] = swap;
        // adopt new position
        loc = newLoc;
    }

}
