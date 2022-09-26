package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;

import java.util.Random;

/**
 * A class that contains game utilities
 */
public class GameUtilities {

    /**
     * Getter for items with capabilities in the actor's inventory
     * @param actor the actor that has his inventory accessed
     * @param capability the capability of the item
     * @return items with capabilities in the actor's inventory
     */
    public static Item getItemWithCapability(Actor actor, Enum<?> capability) {
        return actor.getInventory().stream().filter(item -> item.hasCapability(capability)).findFirst().orElse(null);
    }
}
