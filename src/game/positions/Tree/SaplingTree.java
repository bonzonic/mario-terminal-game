package game.positions.Tree;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ChopAction;
import game.actions.JumpActorAction;
import game.items.Coin;

/**
 * A teenage tree
 */
public class SaplingTree extends Tree {

    /**
     * Constructor.
     */
    public SaplingTree() {
        super('t', 15);
        this.addCapability(Status.CAN_BE_CHOPPED);
    }

    /**
     * Returns the fall damage that is dealt for falling off of this tree.
     * @return fall damage if fallen off this tree.
     */
    @Override
    public int getFallDamageRate() {
        return 20;
    }
    /**
     * Get the probability of not falling of this tree if you fall off when trying to jump on it.
     * @return  probability of not falling off this tree
     */
    @Override
    public double getFallProb() {
        return 0.8;
    }
    /**
     * Returns a string representation of this tree.
     * @return string representation of this tree.
     */
    @Override
    public String toString() {
        return "SaplingTree";
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
        final double COIN_SPAWN_PROB = 0.10;
        final int COIN_DEFAULT_AMOUNT = 20;
        if (random.nextDouble() <= COIN_SPAWN_PROB) {
            location.addItem(new Coin(COIN_DEFAULT_AMOUNT));
        }

        final int EVOLVE_AGE = 10;
        if (this.age == EVOLVE_AGE) {
            location.setGround(new MatureTree());
        }
    }

    @Override
    public int getWoodAmount() {
        return 3;
    }
}
