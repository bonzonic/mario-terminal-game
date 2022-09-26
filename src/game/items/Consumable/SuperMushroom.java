package game.items.Consumable;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ConsumeAction;
import game.items.Tradable;

/**
 * An item which makes mario bigger
 */
public class SuperMushroom extends Consumable implements Tradable {

    /**
     * The action used to consume the Super Mushroom
     */
    private Action consumeAction;

    /**
     * The price of the Super Mushroom in Toad's shop
     */
    private int price = 400;


    /***
     * Constructor and adds the consume action to it also, allowing the player to consume this SuperMushroom
     */
    public SuperMushroom() {
        super("Super Mushroom", '^', false);
        this.consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
    }
    /**
     * returns the status of this SuperMushroom
     * @return a Status called TALL
     */
    private Status effect() {
        return Status.TALL;
    }

    /**
     * Allows the player to consume this SuperMushroom
     * @param actor the player consuming the item
     * @param map - the location the player is at
     * @return an empty string
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.addCapability(this.effect());
        this.removeAction(this.consumeAction);

        // Remove it from your inventory if it is there.
        if (actor.getInventory().contains(this)) {
            actor.removeItemFromInventory(this);
        }
        // If you ate it off the floor, make sure to remove the item on the floor
        else {
            map.locationOf(actor).removeItem(this);
        }
        actor.increaseMaxHp(50);
        return "";
    }

    /**
     * Returns the price of the Item
     * @return price of the SuperMushroom
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * returns the SuperMushroom item
     * @return this instance of the SuperMushroom
     */
    @Override
    public Item getItem() {
        return this;
    }

}
