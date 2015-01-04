/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.portrayal3d.grid.quad;

import edu.gmu.cs.sim.field.grid.DoubleGrid2D;
import edu.gmu.cs.sim.field.grid.Grid2D;
import edu.gmu.cs.sim.field.grid.IntGrid2D;
import edu.gmu.cs.sim.field.grid.ObjectGrid2D;
import edu.gmu.cs.sim.portrayal3d.grid.ValueGrid2DPortrayal3D;

/**
 * Used by ValueGrid2DPortrayal3D to send needed value information to
 * underlying QuadPortrayals.  Specifically, ValueGridCellInfo contains
 * the x,y,z dimensions of the current value point, plus a value() method
 * which returns the current value at that point.
 *
 * @author Catalin Gabriel Balan
 */
public class ValueGridCellInfo {
    /* This could be Grid2D or Grid3D */
    Object grid;
    ValueGrid2DPortrayal3D fieldPortrayal;
    public int x = 0;
    public int y = 0;

    public ValueGridCellInfo(ValueGrid2DPortrayal3D fieldPortrayal, Grid2D g) {
        grid = g;
        this.fieldPortrayal = fieldPortrayal;
    }

    public double value() {
        if (grid instanceof DoubleGrid2D) {
            return ((DoubleGrid2D) grid).field[x][y];
        } else if (grid instanceof IntGrid2D) {
            return ((IntGrid2D) grid).field[x][y];
        } else if (grid instanceof ObjectGrid2D) {
            return fieldPortrayal.doubleValue(((ObjectGrid2D) grid).field[x][y]);
        } else {
            return 0;  // an error
        }
    }
}