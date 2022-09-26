package game.items.Consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * The item that can be consumed will be implementing this interface
 */
public abstract class Consumable extends Item {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Consumable(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    // Design rationale: Having this as a interface method to enforce
    // that all Consumables must give you a new status.

    /**
     * The player consuming the item
     * @param actor the player consuming the item
     * @param map - the location the player is at
     * @return a String specifying that the actor has consumed it
     */
    public abstract String consume(Actor actor, GameMap map);
}
