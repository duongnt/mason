/* 
 * Copyright 2011 by Mark Coletti, Keith Sullivan, Sean Luke, and
 * George Mason University Mason University Licensed under the Academic
 * Free License version 3.0
 *
 * See the file "LICENSE" for more information
 *
 * $Id: NetworkWorldWithUI.java 682 2012-06-24 22:12:40Z mcoletti $
*/
package edu.gmu.cs.sim.app.geo.networkworld;

import javax.swing.*;
import java.awt.*;

import edu.gmu.cs.sim.display.Console;
import edu.gmu.cs.sim.display.Controller;
import edu.gmu.cs.sim.display.Display2D;
import edu.gmu.cs.sim.display.GUIState;
import edu.gmu.cs.sim.engine.SimState;
import edu.gmu.cs.sim.portrayal.geo.GeomPortrayal;
import edu.gmu.cs.sim.portrayal.geo.GeomVectorFieldPortrayal;


/**
 *  MASON GUI wrapper for the NetworkWorld GeoMASON example.   The only addition to a standard MASON GUIState 
 *  is the addition of checking the minimum bounding rectangles (MBRs) for each portrayal.  This check ensures that 
 *  the MBRs defining each field are matched, so that during display, all the fields line up (ie., you actually see
 *  the agent moving along the network).  
 */
public class NetworkWorldWithUI extends GUIState {

    private Display2D display;
    private JFrame displayFrame;

    private GeomVectorFieldPortrayal geometryPortrayal = new GeomVectorFieldPortrayal();
    private GeomVectorFieldPortrayal intersectionPortrayal = new GeomVectorFieldPortrayal();
    private GeomVectorFieldPortrayal agentPortrayal = new GeomVectorFieldPortrayal();


    public NetworkWorldWithUI(SimState state) {
        super(state);
    }

    public NetworkWorldWithUI() {
        super(new NetworkWorld(System.currentTimeMillis()));
    }

    public void init(Controller controller) {
        super.init(controller);

        display = new Display2D(NetworkWorld.WIDTH, NetworkWorld.HEIGHT, this);
        display.attach(geometryPortrayal, "World");
        display.attach(intersectionPortrayal, "Intersections");
        display.attach(agentPortrayal, "Agent");

        displayFrame = display.createFrame();
        controller.registerFrame(displayFrame);
        displayFrame.setVisible(true);
    }

    public void start() {
        super.start();
        setupPortrayals();
    }

    private void setupPortrayals() {
        NetworkWorld world = (NetworkWorld) state;

        geometryPortrayal.setField(world.world);

        intersectionPortrayal.setField(world.junctions);
        intersectionPortrayal.setPortrayalForAll(new GeomPortrayal(Color.LIGHT_GRAY, true));

        agentPortrayal.setField(world.agents);
        agentPortrayal.setPortrayalForAll(new GeomPortrayal(Color.RED, true));

        display.reset();
        display.setBackdrop(Color.WHITE);
        display.repaint();
    }


    public static void main(String[] args) {
        NetworkWorldWithUI worldGUI = new NetworkWorldWithUI();
        Console console = new Console(worldGUI);
        console.setVisible(true);
    }

}
