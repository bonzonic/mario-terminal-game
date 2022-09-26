package game.items;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;

/**
 * A class that contains the attributes of a Wrench item.
 */
public class Wrench extends Item implements Weapon, Tradable {
    private int price = 200;

    /***
     * Constructor.
     */
    public Wrench() {
        super("Wrench", 'w', true);
    }

    /**
     * It deals 50 damage
     * @return an integer of 50
     */
    @Override
    public int damage() {
        return 50;
    }

    /**
     * deals 80 damage
     * @return an integer of 80
     */
    @Override
    public int chanceToHit() {
        return 80;
    }

    /**
     * more than 1 wrench callled wrenches
     * @return a string named "wrenches"
     */
    @Override
    public String verb(){
        return "wrenches";
    }

    /**
     * returns the price of this item
     * @return an integer representing the price of the Wrench
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Returns this instance of Wrench
     * @return this wrench object
     */
    @Override
    public Item getItem() {
        return this;
    }
}