/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.app.wcss.tutorial05;

import edu.gmu.cs.sim.engine.SimState;
import edu.gmu.cs.sim.engine.Steppable;
import edu.gmu.cs.sim.field.continuous.Continuous2D;
import edu.gmu.cs.sim.util.Double2D;
import edu.gmu.cs.sim.util.MutableDouble2D;

public class Student implements Steppable {
    private static final long serialVersionUID = 1;

    public void step(SimState state) {
        Students students = (Students) state;
        Continuous2D yard = students.yard;

        Double2D me = students.yard.getObjectLocation(this);

        MutableDouble2D sumForces = new MutableDouble2D();

        // add in a vector to the "teacher" -- the center of the yard, so we don't go too far away
        sumForces.addIn(new Double2D((yard.width * 0.5 - me.x) * students.forceToSchoolMultiplier,
            (yard.height * 0.5 - me.y) * students.forceToSchoolMultiplier));

        // add a bit of randomness
        sumForces.addIn(new Double2D(students.randomMultiplier * (students.random.nextDouble() * 1.0 - 0.5),
            students.randomMultiplier * (students.random.nextDouble() * 1.0 - 0.5)));

        sumForces.addIn(me);

        students.yard.setObjectLocation(this, new Double2D(sumForces));
    }
}
    
