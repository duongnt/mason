/*
  Copyright 2014 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.portrayal.inspector;

import edu.gmu.cs.sim.display.GUIState;
import edu.gmu.cs.sim.portrayal.Inspector;

public interface ProvidesInspector {
    /** Provides an inspector for this object.  The provided name should appear prominently,
     typically as a TitledBorder around the inspector. */
    public Inspector provideInspector(GUIState state, String name);
}
