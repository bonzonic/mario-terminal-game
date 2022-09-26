package game.items;

import edu.monash.fit2099.engine.actors.Actor;
        import edu.monash.fit2099.engine.items.Item;
        import edu.monash.fit2099.engine.items.PickUpItemAction;
        import edu.monash.fit2099.engine.positions.Location;
        import game.GameUtilities;
        import game.actions.PickUpStackableAction;
        import game.reset.Resettable;
        import game.Status;

        import java.util.Stack;

/**
 * The class represents a coin
 */
public class Coin extends Item implements Resettable, Stackable {

    /**
     * The value of the coin
     */
    // Note: amount may be an integer.
    private int amount;

    /**
     * Constructor
     * @param amount - an integer representing the amount of the coin
     */
    public Coin(int amount) {
        super("Coin (" + amount + ")", '$', true);
        this.amount = amount;
        registerInstance();
    }

    /**
     * A getter to get the amount of the coin
     * @return an integer representing the amount of the coin
     */
    @Override
    public int getAmount(){
        return amount;
    }

    /**
     * Returns storable coins
     * @return storable coins
     */
    @Override
    public Storable getStorableType() {
        return Storable.COIN;
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
     * If the status is Resettable, then remove the coin from the ground
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        if (this.hasCapability(Status.RESETTABLE)) {
            currentLocation.removeItem(this);
            this.removeCapability(Status.RESETTABLE);
        }
    }

    /**
     * Adds coins to the Player's magic pouch
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        MagicPouch magicPouch = (MagicPouch) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_STORABLES);
        magicPouch.increaseAmount(Storable.COIN, this.getAmount());
        actor.removeItemFromInventory(this);
    }

    /**
     * Adds the capability RESETTABLE to this class
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }
}
