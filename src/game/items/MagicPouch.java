package game.items;

import edu.monash.fit2099.engine.items.Item;
import game.Status;

import java.util.HashMap;

/**
 * A magical pouch that can store wood, coins and arrows!
 */
public class MagicPouch extends Item {

    /**
     * The HashMap that stores the storable items
     */
    private HashMap<Storable, Integer> counter = new HashMap<>();

    /***
     * Constructor.
     */
    public MagicPouch() {
        super("Magic Pouch", 'P', false);
        this.addCapability(Status.CAN_CARRY_STORABLES);
    }

    /**
     * Get the amount of the storable item
     * @param item - storable item
     * @return the amount of the storable item
     */
    public int getAmount(Storable item) {
        return counter.getOrDefault(item, 0);
    }

    /**
     * Setter for the Storable item
     * @param item - Storable item
     * @param amount - the amount of the Storable item
     */
    public void setAmount(Storable item, int amount) {
        counter.put(item, amount);
    }

    /**
     * Increase the amount of the storable item by amount
     * @param item - Storable Item
     * @param amount - the amount of the Storable item to be increased
     */
    public void increaseAmount(Storable item, int amount) {
        counter.put(item, this.getAmount(item) + amount);
    }

    /**
     * Decrease the number of Storable item based on the amount
     * @param item - the Storable item
     * @param amount - an integer representing the amount of the Storable item should be decreased
     */
    public void decreaseAmount(Storable item, int amount) {
        counter.put(item, this.getAmount(item) - amount);
    }
}
