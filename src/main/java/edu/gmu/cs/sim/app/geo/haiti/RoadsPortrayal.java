/*
    RoadsPortrayal.java

    $Id$
*/

package edu.gmu.cs.sim.app.geo.haiti;


import java.awt.*;
import java.awt.geom.Rectangle2D;

import edu.gmu.cs.sim.portrayal.DrawInfo2D;
import edu.gmu.cs.sim.portrayal.simple.RectanglePortrayal2D;
import edu.gmu.cs.sim.util.MutableDouble;
import edu.gmu.cs.sim.util.gui.ColorMap;
import edu.gmu.cs.sim.util.gui.SimpleColorMap;


public class RoadsPortrayal extends RectanglePortrayal2D {
    // colormap for roads, which have values between 0 and 12
    public static ColorMap RoadsColor = new SimpleColorMap(
        0, 50, new Color(100, 100, 50), new Color(0, 0, 0));
    private static final long serialVersionUID = 1L;


    Color rColor = new Color(100, 100, 50);


    @Override
    public void draw(Object object, Graphics2D graphics, DrawInfo2D info) {
        if (object == null) {
            return;
        }

        Rectangle2D.Double draw = info.draw;
        final double width = draw.width * scale;
        final double height = draw.height * scale;

        final int x = (int) (draw.x - width / 2.0);
        final int y = (int) (draw.y - height / 2.0);
        final int w = (int) (width);
        final int h = (int) (height);

        MutableDouble i = (MutableDouble) object;
        if (i.intValue() > -1) {
            graphics.setColor(RoadsColor.getColor(i.intValue()));
            graphics.fillRect(x, y, w, h);
        }
    }

}
