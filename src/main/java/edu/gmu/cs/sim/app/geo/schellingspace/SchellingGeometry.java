/**
 ** SchellingGeometry.java
 **
 ** Copyright 2011 by Sarah Wise, Mark Coletti, Andrew Crooks, and
 ** George Mason University.
 **
 ** Licensed under the Academic Free License version 3.0
 **
 ** See the file "LICENSE" for more information
 **
 ** $Id: SchellingGeometry.java 842 2012-12-18 01:09:18Z mcoletti $
 **/
package edu.gmu.cs.sim.app.geo.schellingspace;

import java.util.ArrayList;

import edu.gmu.cs.sim.util.geo.MasonGeometry;


public class SchellingGeometry extends MasonGeometry {

    private int id = -1;

    private String soc;

    public int initRed = 0, initBlue = 0;

    public ArrayList<Person> residents;
    public ArrayList<SchellingGeometry> neighbors;


    public SchellingGeometry() {
        super();
        residents = new ArrayList<Person>();
        neighbors = new ArrayList<SchellingGeometry>();
    }


    public void init() {
        initRed = getIntegerAttribute("RED");
        initBlue = getIntegerAttribute("BLUE");
    }


    int getID() {
        return getDoubleAttribute("ID_ID").intValue();
    }


    String getSoc() {
        return getStringAttribute("SOC");
    }

}