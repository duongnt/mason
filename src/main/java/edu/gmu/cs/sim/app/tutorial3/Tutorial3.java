/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.app.tutorial3;

import edu.gmu.cs.sim.engine.Schedule;
import edu.gmu.cs.sim.engine.SimState;
import edu.gmu.cs.sim.engine.Steppable;
import edu.gmu.cs.sim.field.grid.DoubleGrid2D;
import edu.gmu.cs.sim.field.grid.SparseGrid2D;
import edu.gmu.cs.sim.util.Int2D;


public class Tutorial3 extends SimState {
    private static final long serialVersionUID = 1;

    public DoubleGrid2D trails;
    public SparseGrid2D particles;

    public int gridWidth = 100;
    public int gridHeight = 100;
    public int numParticles = 500;

    public Tutorial3(long seed) {
        super(seed);
    }

    public void start() {
        super.start();
        trails = new DoubleGrid2D(gridWidth, gridHeight);
        particles = new SparseGrid2D(gridWidth, gridHeight);

        Particle p;

        for (int i = 0; i < numParticles; i++) {
            p = new Particle(random.nextInt(3) - 1, random.nextInt(3) - 1);  // random direction
            schedule.scheduleRepeating(p);
            particles.setObjectLocation(p,
                new Int2D(random.nextInt(gridWidth), random.nextInt(gridHeight)));  // random location
        }

        // Schedule the decreaser
        Steppable decreaser = new Steppable() {
            private static final long serialVersionUID = 1;

            public void step(SimState state) {
                // decrease the trails
                trails.multiply(0.9);
            }
        };

        schedule.scheduleRepeating(Schedule.EPOCH, 2, decreaser, 1);
    }

    public static void main(String[] args) {
        doLoop(Tutorial3.class, args);
        System.exit(0);
    }
}
