package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.JumpActorAction;
import game.items.Coin;

import java.util.Random;

/**
 * An abstract class used to provide a singular identity for high grounds
 * that the Player can attempt to jump onto.
 */
public abstract class HigherGround extends Ground {

    /**
     * Getter for the fall damage for falling off the high ground
     * @return the fall damage for falling off the high ground
     */
    public abstract int getFallDamageRate();

    /**
     * Getter for the probability of falling off the high ground
     * @return the probabilityof falling off the high ground
     */
    public abstract double getFallProb();


    /**
     * Constructor
     * @param displayChar character to display for this type of terrain
     */
    public HigherGround(char displayChar) {
        super(displayChar);
    }

    public String jumpOn(Actor jumper, GameMap map, Location destination) {
        double successRate = this.getFallProb();
        int damage = this.getFallDamageRate();
        if (jumper.hasCapability(Status.TALL)) {
            successRate = 1.0;
            // This line is redundant as the Player won't take damage due to the success rate
            damage = 0;
        }
        Random random = new Random();
        if (random.nextDouble() <= successRate){
            map.moveActor(jumper, destination);
            return String.format("%s jumps to %s(%d, %d)", jumper, this, destination.x(), destination.y());
        }
        else{
            jumper.hurt(damage);
            return String.format("%s hits the %s and takes %d damage", jumper, this.toString().toLowerCase(), damage);
        }

    }

    /**
     * Checks whether the actor can go on the higher ground.
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.CAN_FLY);
    }

    /**
     * Returns an empty Action list.
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        if (!direction.isEmpty())
            actions.add(new JumpActorAction(location, direction, this));
        return actions;
    }
}