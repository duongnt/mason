/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.app.tutorial7;

import edu.gmu.cs.sim.engine.SimState;
import edu.gmu.cs.sim.engine.Steppable;
import edu.gmu.cs.sim.field.grid.SparseGrid3D;
import edu.gmu.cs.sim.util.Int3D;

public class Fly implements Steppable {
    private static final long serialVersionUID = 1;

    public void step(SimState state) {
        Tutorial7 tut = (Tutorial7) state;

        SparseGrid3D flies = tut.flies;

        // Move me in a random direction
        Int3D myLoc = flies.getObjectLocation(this);
        int x = flies.stx(myLoc.x + (tut.random.nextBoolean() ? 1 : -1));
        int y = flies.sty(myLoc.y + (tut.random.nextBoolean() ? 1 : -1));
        int z = flies.stz(myLoc.z + (tut.random.nextBoolean() ? 1 : -1));

        flies.setObjectLocation(this, new Int3D(x, y, z));

        // Update the projections with, I dunno, some function based on my position :-)
        tut.xProjection.field[y][z] += Math.log(x + 2);
        tut.yProjection.field[x][z] += Math.log(y + 2);
        tut.zProjection.field[x][y] += Math.log(z + 2);
    }
}
    
