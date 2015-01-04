package edu.gmu.cs.sim.app.geo.haiti;


import edu.gmu.cs.sim.engine.SimState;
import edu.gmu.cs.sim.engine.Steppable;
import edu.gmu.cs.sim.field.grid.IntGrid2D;
import edu.gmu.cs.sim.util.Bag;


public class Center implements Steppable {
    private static final long serialVersionUID = 1L;


    public static int ENERGY_FROM_FOOD = 1000;

    Location loc;


    public int foodLevel;


    int foodIncrement = 1;


    IntGrid2D gradient;


    public Center(int x, int y, int foodLevel) {
        loc = new Location(x, y);
        this.foodLevel = foodLevel;
    }


    public Center(int x, int y, int foodLevel, int energy) {
        loc = new Location(x, y);
        this.foodLevel = foodLevel;
        ENERGY_FROM_FOOD = energy;
    }


    @Override
    public void step(SimState state) {

        if (foodLevel <= 0) {
            return; // if there's no food, don't bother with anything else
        }
        HaitiFood world = (HaitiFood) state;

        Bag peopleInCenter = world.population.getObjectsAtLocation(loc.x, loc.y);
        if (peopleInCenter == null || peopleInCenter.size() == 0) {
            return;
        }

        // distribute food to people in center
        for (Object o : peopleInCenter) {
            Agent a = (Agent) o;

            if (!a.hasFood) { // give hungry agents food

                // give it the food
                a.hasFood = true;
                a.energyLevel += ENERGY_FROM_FOOD;

                // update Center's info about its own food reserves
                foodLevel -= foodIncrement;
                if (foodLevel <= 0) {
                    return; // have we run out of food?
                }
            }
        }
    }


    public int getFoodLevel() {
        return foodLevel;
    }


    public void setFoodLevel(int fl) {
        foodLevel = fl;
    }


}