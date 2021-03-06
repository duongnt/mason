/*
  Copyright 2006 by Sean Luke and George Mason University
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/

package edu.gmu.cs.sim.portrayal3d.continuous;

import javax.vecmath.Vector3d;

import com.sun.j3d.utils.picking.PickIntersection;
import com.sun.j3d.utils.picking.PickResult;
import edu.gmu.cs.sim.field.continuous.Continuous2D;
import edu.gmu.cs.sim.field.continuous.Continuous3D;
import edu.gmu.cs.sim.portrayal.LocationWrapper;
import edu.gmu.cs.sim.portrayal.inspector.StableDouble2D;
import edu.gmu.cs.sim.portrayal.inspector.StableLocation;
import edu.gmu.cs.sim.portrayal3d.SparseFieldPortrayal3D;
import edu.gmu.cs.sim.portrayal3d.inspector.StableDouble3D;
import edu.gmu.cs.sim.util.Double2D;
import edu.gmu.cs.sim.util.Double3D;

/**
 * Portrays both Continuous2D and Continuous3D fields. 
 * 2D fields are spread through the XY plane and are presumed to have Z=0.
 *
 * @author Gabriel Balan
 */
public class ContinuousPortrayal3D extends SparseFieldPortrayal3D {
    /**
     * @see sim.portrayal.SparseFieldPortrayal3D#getLocationOfObjectAsVector3d(Object)
     */
    public Vector3d getLocationOfObjectAsVector3d(Object obj, Vector3d putInHere) {
        if (field instanceof Continuous2D) {
            Double2D locationD2d = ((Continuous2D) field).getObjectLocation(obj);
            if (locationD2d == null) {
                return null;
            }
            putInHere.x = locationD2d.x;
            putInHere.y = locationD2d.y;
            putInHere.z = 0;
        } else {
            Double3D locationD3d = ((Continuous3D) field).getObjectLocation(obj);
            if (locationD3d == null) {
                return null;
            }
            putInHere.x = locationD3d.x;
            putInHere.y = locationD3d.y;
            putInHere.z = locationD3d.z;
        }
        return putInHere;
    }

    public void setField(Object field) {
        if (field instanceof Continuous3D || field instanceof Continuous2D) {
            super.setField(field);
        } else {
            throw new RuntimeException("Invalid field for ContinuousPortrayal3D: " + field);
        }
    }

    public LocationWrapper completedWrapper(LocationWrapper w, PickIntersection pi, PickResult pr) {
        final Object field = getField();
        StableLocation d = null;
        if (field instanceof Continuous2D) {
            d = new StableDouble2D((Continuous2D) field, w.getObject());
        } else {
            d = new StableDouble3D((Continuous3D) field, w.getObject());
        }
        final StableLocation loc = d;
        return new LocationWrapper(w.getObject(), null, this)  // don't care about location
        {
            public Object getLocation() {
                //loc.update();
                return loc;
            }

            public String getLocationName() {
                //loc.update();
                return loc.toString();
            }
        };
    }
}
