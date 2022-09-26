package game.items;

/**
 * Items can be stacked, for instance the ground may have 2 wood but only 1 item
 */
public interface Stackable {

    /**
     * The amount of the Stackable item
     * @return an integer
     */
    int getAmount();

    /**
     * Gettter for the Storable item
     * @return a Storable item
     */
    Storable getStorableType();
}
