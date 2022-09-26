package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.PickUpStackableAction;
import game.reset.Resettable;

/**
 * Literally just wood, can be used to craft items at a crafting bench
 */
public class Wood extends Item implements Resettable, Stackable{

    /**
     * The number of Wood
     */
    private int woodAmount;

    /**
     * A constructor for the Wood class
     */
    public Wood(int woodAmount){
        super("Wood (" + woodAmount + ")", 'W', false);
        this.woodAmount = woodAmount;
        registerInstance();
    }

    /**
     * Gets the amount of wood in this item. (It can have multiple wood in a single item)
     * @return int - represents the wood amount
     */
    @Override
    public int getAmount() {
        return woodAmount;
    }

    /**
     * Get the storable type
     * @return a Storable enum of wood
     */
    @Override
    public Storable getStorableType() {
        return Storable.WOOD;
    }

    /**
     * If the status is Resettable, then remove the wood from the ground
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
    public DropItemAction getDropAction(Actor actor){
        return null;
    }

    @Override
    public PickUpItemAction getPickUpAction(final Actor actor) {
        return new PickUpStackableAction(this, this);
    }

    /**
     * Adds the capability RESETTABLE to this class
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }
}
