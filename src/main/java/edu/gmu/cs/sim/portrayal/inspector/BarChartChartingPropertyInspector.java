/*
  Copyright 2013 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.portrayal.inspector;

import java.awt.*;

import edu.gmu.cs.sim.display.GUIState;
import edu.gmu.cs.sim.engine.Stoppable;
import edu.gmu.cs.sim.util.Properties;
import edu.gmu.cs.sim.util.media.chart.BarChartGenerator;
import edu.gmu.cs.sim.util.media.chart.ChartGenerator;

/** A property inspector which generates various bar charts of data.  The bar charts update in real-time as
 requested by the user.  Data properties for which
 the BarChartChartingPropertyInspector will operate include:

 <ul>
 <li>Any array of Objects
 </ul>

 <p>BarChartChartingPropertyInspector registers itself with the property menu option "Make Bar Chart".
 */

public class BarChartChartingPropertyInspector extends PieChartChartingPropertyInspector {
    protected boolean validChartGenerator(ChartGenerator generator) {
        return generator instanceof BarChartGenerator;
    }

    public static String name() {
        return "Make Bar Chart";
    }

    public static Class[] types() {
        return new Class[]
            {
                new Object[0].getClass(), java.util.Collection.class,
            };
    }

    public BarChartChartingPropertyInspector(Properties properties, int index, Frame parent, final GUIState simulation) {
        super(properties, index, parent, simulation);
    }

    public BarChartChartingPropertyInspector(Properties properties, int index, final GUIState simulation, ChartGenerator generator) {
        super(properties, index, simulation, generator);
    }

    protected ChartGenerator createNewGenerator() {
        return new BarChartGenerator() {
            public void quit() {
                super.quit();
                Stoppable stopper = getStopper();
                if (stopper != null) {
                    stopper.stop();
                }

                // remove the chart from the GUIState's charts
                getCharts(simulation).remove(this);
            }
        };
    }
}
