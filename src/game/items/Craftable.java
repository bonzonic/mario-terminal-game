package game.items;

import edu.monash.fit2099.engine.items.Item;

import java.util.HashMap;

/**
 * Any items that can be crafted at the Crafting Table will implement this interface
 */
public interface Craftable {

    /**
     * Returns the recipe for crafting the item
     * @return the recipe for crafting the item
     */
    HashMap<Storable, Integer> getRecipe();

    /**
     * Returns the crafted item
     * @return the crafted item
     */
    Item getCrafted();

    /**
     * Returns the name of the item
     * @return the name of the item
     */
    String toString();
}
