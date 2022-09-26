package game.items.Weapon;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.GameUtilities;
import game.Status;
import game.actions.PickUpStackableAction;
import game.items.MagicPouch;
import game.items.Stackable;
import game.items.Storable;
import game.items.Tradable;
import game.reset.Resettable;

/**
 * A stick with a sharp metal tip used for killing
 */
public class Arrow extends Item implements Tradable, Stackable, Resettable {

    /**
     * The price of an arrow
     */
    private int price = 40;

    /**
     * The number of arrows
     */
    private int arrowCount;

    /**
     * Constructor
     */
    public Arrow(int arrowCount){
        super("Arrow", 'A', false);
        this.arrowCount = arrowCount;
        registerInstance();
    }

    /**
     * Returns a new instance of PickUpStorableAction
     * @param actor - the actor picking up this coin
     * @return a PickUpStorable instance to pick up this instance
     */
    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return new PickUpStackableAction(this, this);
    }

    /**
     * Getter for the price of an Arrow
     * @return the price of an Arrow
     */
    @Override
    public int getPrice() {
        return this.price;
    }

    /**
     * Getter for Arrow
     * @return Arrow
     */
    @Override
    public Item getItem() {
        return this;
    }

    /**
     * Returns the number of arrows
     * @return the number of arrows
     */
    @Override
    public int getAmount() {
        return this.arrowCount;
    }

    /**
     * Returns storable arrows
     * @return storable arrows
     */
    @Override
    public Storable getStorableType() {
        return Storable.ARROW;
    }

    /**
     * Adds arrows to the Player's magic pouch
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        MagicPouch magicPouch = (MagicPouch) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_STORABLES);
        magicPouch.increaseAmount(Storable.ARROW, this.getAmount());
        actor.removeItemFromInventory(this);
    }

    /**
     * If the status is Resettable, then remove the arrow from the ground
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESETTABLE)) {
            currentLocation.removeItem(this);
            this.removeCapability(Status.RESETTABLE);
        }
    }

    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }
}
