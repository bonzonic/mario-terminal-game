package game.positions.Fountain;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.GameUtilities;
import game.Status;
import game.actions.FillBottleAction;
import game.items.Consumable.Consumable;
import game.positions.Dirt;

/**
 * A fountain that contains drinkable liquid
 */
public abstract class Fountain extends Ground {

    /**
     * The maximum capacity of the fountain
     */
    private int maxCapacity;

    /**
     * The current capacity of the fountain
     */
    private int capacity;

    /**
     * Used to indicate whether the fountain needs to e refilled.
     */
    private boolean refill = false;

    /**
     * Used to indicate how long the fountain has been empty (no liquid inside).
     */
    private int refillCounter = 0;

    /**
     * The amount of ticks it takes for a fountain to replenish itself after being empty.
     */
    final private int REFILL_TICKS = 5;

    /**
     * The contents of the fountain
     */
    private Consumable contents;

    /**
     * Constructor.
     */
    public Fountain(char displayChar, int maxCapacity, Consumable contents) {
        super(displayChar);
        // I know The specs said to hard-code max capacity to 10, but I'm letting the child class decide for flexibility.
        this.maxCapacity = maxCapacity;
        this.capacity = this.maxCapacity;
        this.contents = contents;
        this.addCapability(Status.CAN_BE_DRUNK);
    }

    /**
     * Starts the timer for the Fountain to refill its contents of liquids.
     */
    private void startRefillProcess() {
        this.refill = true;
        this.refillCounter = this.REFILL_TICKS;
    }

    /**
     * Stops the timer for the Fountain to refill its contents of liquids.
     * and adds the contents to its maximum amount of liquid in the fountain.
     */
    private void stopRefillProcess() {
        this.refill = false;
        this.capacity = this.maxCapacity;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList ret = new ActionList();
        if (this.capacity == 0) {
            if (!this.refill) {
                this.startRefillProcess();
            }
            return ret;
        }
        boolean hasBottle = GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_LIQUIDS) != null;
        if (location.getActor() == actor && hasBottle) {
            ret.add(new FillBottleAction(this));
        }
        return ret;
    }

    @Override
    public void tick(final Location location) {
        if (this.refill) {
            this.refillCounter--;
            if (this.refillCounter == 0) {
                this.stopRefillProcess();
            }
        }
    }

    @Override
    public String toString() {
        return "Fountain (%d/%d)".formatted(capacity, maxCapacity);
    }

    /**
     * Getter for the contents of the fountain
     * @return the contents of the fountain
     */
    public Consumable getContents() {
        return this.contents;
    }

    /**
     * Reduces the current capacity of the fountain
     */
    public void reduceCapacity( int amount) {
        this.capacity -= amount;
    }
}
