package game.positions.Tree;

import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;

/**
 * A baby tree
 */
public class SproutTree extends Tree {

    /**
     * Constructor.
     */
    public SproutTree() {
        super('+', 1);
    }

    /**
     * Returns the fall damage that is dealt for falling off of this tree.
     * @return fall damage if fallen off this tree.
     */
    @Override
    public int getFallDamageRate() {
        return 10;
    }

    /**
     * Get the probability of not falling of this tree if you fall off when trying to jump on it.
     * @return  probability of not falling off this tree
     */
    @Override
    public double getFallProb() {
        return 0.9;
    }

    /**
     * Returns a string representation of this tree.
     * @return string representation of this tree.
     */
    @Override
    public String toString() {
        return "SproutTree";
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
        final double GOOMBA_SPAWN_PROB = 0.10;
        if (!location.containsAnActor()
                && random.nextDouble() <= GOOMBA_SPAWN_PROB) {
            location.addActor(new Goomba());
        }

        final int EVOLVE_AGE = 10;
        if (this.age == EVOLVE_AGE) {
            location.setGround(new SaplingTree());
        }
    }
}
