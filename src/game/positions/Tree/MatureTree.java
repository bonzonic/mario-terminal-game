package game.positions.Tree;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ChopAction;
import game.actors.FlyingKoopa;
import game.actors.Koopa;
import game.positions.Dirt;
import game.positions.GroundCharacteristics;

import java.util.ArrayList;

/**
 * An adult tree
 */
public class MatureTree extends Tree {

    /**
     * Constructor.
     */
    public MatureTree() {
        super('T', 25);
        this.addCapability(Status.CAN_BE_CHOPPED);
    }

    /**
     * Returns the fall damage that is dealt for falling off of this tree.
     * @return fall damage if fallen off this tree.
     */
    @Override
    public int getFallDamageRate() {
        return 30;
    }
    /**
     * Get the probability of not falling of this tree if you fall off when trying to jump on it.
     * @return  probability of not falling off this tree
     */
    @Override
    public double getFallProb() {
        return 0.7;
    }
    /**
     * Returns a string representation of this tree.
     * @return string representation of this tree.
     */
    @Override
    public String toString() {
        return "MatureTree";
    }
    /**
     * Goes through the tick of a tree.
     * @param location Location of the tree
     */
    @Override
    public void tick(Location location) {
        // Parent function handles the coins spawning if tree is destroyed using a power star, and
        // other stuff that is common to all Tree and HigherGround classes.
        super.tick(location);

        final double KOOPA_SPAWN_PROB = 0.15;
        final double DIE_PROB = 0.20;
        final double KOOPA_VARIATION_PROB = 0.50;

        final int SPROUT_SPAWN_CYCLE = 5;
        if (!location.containsAnActor() &&
                random.nextDouble() <= KOOPA_SPAWN_PROB) {
            if (random.nextDouble() <= KOOPA_VARIATION_PROB) {
                location.addActor(new Koopa());
            }
            else{
                location.addActor(new FlyingKoopa());
            }
        }

        if (age % SPROUT_SPAWN_CYCLE == 0) {

            ArrayList<Location> fertileSurroundings = new ArrayList<>();
            for (Exit exit : location.getExits()) {
                Location curLocation = exit.getDestination();
                Ground curGround = curLocation.getGround();
                if (curGround.hasCapability(GroundCharacteristics.FERTILE)) {
                    fertileSurroundings.add(curLocation);
                }
            }
            if (!fertileSurroundings.isEmpty()) {
                Location sproutSpawnLocation = fertileSurroundings
                        .get(this.random.nextInt(fertileSurroundings.size()));
                sproutSpawnLocation.setGround(new SproutTree());
            }
        }

        // Wither and die
        if (this.random.nextDouble() <= DIE_PROB) {
            location.setGround(new Dirt());
        }
    }

    @Override
    public int getWoodAmount() {
        return 5;
    }
}
