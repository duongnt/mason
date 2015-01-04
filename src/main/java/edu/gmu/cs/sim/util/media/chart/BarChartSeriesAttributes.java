/*
  Copyright 2013 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.util.media.chart;

import org.jfree.data.general.SeriesChangeListener;

// From JFreeChart

/**
 A SeriesAttributes used for user control of bar chart series created with BarChartGenerator.  Similar to PieChartSeriesAttributes.
 */


public class BarChartSeriesAttributes extends PieChartSeriesAttributes {
    /** Produces a PieChartSeriesAttributes object */
    public BarChartSeriesAttributes(ChartGenerator generator, String name, int index, SeriesChangeListener stoppable)  // , boolean includeMargin)
    {
        super(generator, name, index, stoppable);
    }
}
