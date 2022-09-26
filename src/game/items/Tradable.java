package game.items;

import edu.monash.fit2099.engine.items.Item;

/**
 * Any items which can be traded with toad will implement this interface
 */
public interface Tradable {

    /**
     * Returns the price of the item
     * @return an integer representing the price
     */
    int getPrice();

    /**
     * Returns the item
     * @return Item - an item which is returned
     */
    Item getItem();

    /**
     * @return a String which is the name of the tradable item
     */
    String toString();
}