package game.items.Consumable;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;
import game.items.Tradable;

/**
 * An item that grants mario invincibility for 10 rounds
 */
public class PowerStar extends Consumable implements Tradable {
    private int age = 0;
    private final int EXPIRY_TURNS = 10;
    private Action consumeAction;
    private int price = 600;

    /**
     * Constructor and also adds the action that the player can consume it
     */
    public PowerStar() {
        super("Power Star", '*', true);
        this.consumeAction = new ConsumeAction(this);
        this.addAction(this.consumeAction);
    }

    /**
     * Power Star can't be dropped, so we override it and return null
     * @param actor the player
     * @return null
     */
    @Override
    public DropItemAction getDropAction(Actor actor) {
        return null;
    }

    /**
     * Grants the player the POWER_STAR effect
     * @return a Status enum of POWER_STAR
     */
    private Status effect() {
        return Status.POWER_STAR;
    }

    /**
     * After 10 turns, the effect will be removed and removed from the Inventory
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        age++;
        if (age >= EXPIRY_TURNS) {
            actor.removeCapability(this.effect());
            actor.removeItemFromInventory(this);
        }
    }

    @Override
    /**
     * After 10 turns, the effect will be removed and removed from the map.
     * @param currentLocation The location of this Item on the map.
     */
    public void tick(Location currentLocation) {
        age++;
        if (age >= EXPIRY_TURNS) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * The player consuming the PowerStar
     * @param actor the player consuming the item
     * @param map - the location the player is at
     * @return a String indicating how many turns remaining for the power star
     */
    @Override
    public String consume(Actor actor, GameMap map) {
        actor.addCapability(this.effect());

        actor.heal(200);
        // Don't allow to consume again- even if it's still in your inventory.

        this.removeAction(this.consumeAction);
        // It is possible
        // to consume directly without putting it in your inventory.
        // Add to inventory and remove from floor.
        if (!actor.getInventory().contains(this)) {
            actor.addItemToInventory(this);
            map.locationOf(actor).removeItem(this);
        }

        return String.format("%d turns remaining", EXPIRY_TURNS) ;
    }

    /**
     * Returns the price of this Power Star
     * @return an integer representing the price of the item
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * @return this PowerStar
     */
    @Override
    public Item getItem() {
        return this;
    }
}
