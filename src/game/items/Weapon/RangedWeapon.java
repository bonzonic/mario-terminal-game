package game.items.Weapon;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actions.RangedAttackAction;
import game.positions.GroundCharacteristics;

import java.util.ArrayList;
import java.util.List;

/**
 * A blueprint for weapons that are ranged
 */
public abstract class RangedWeapon extends Item implements Weapon {

    /**
     * An ActionList that contains all the actions for shooting
     */
    protected ActionList shootActions = new ActionList();

    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public RangedWeapon(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
    }

    /**
     * Getter for the range of the target from the actor
     * @return range of the target from the actor
     */
    public abstract int getRange();

    /**
     * Getter for the amount of ammo in the ranged weapon
     * @param actor the actor that holds the ranged weapon
     * @return the amount of ammo in the ranged weapon
     */
    public abstract int getAmmoAmount(Actor actor);

    /**
     * @return a string for the shooting verb
     */
    @Override
    public abstract String verb();

    /**
     * Reduces the ammo once it was used
     * @param actor - the actor shooting the arrow
     * @param amount - the amount of ammo shot
     */
    public abstract void reduceAmmo(Actor actor, int amount);

    /**
     * The damage dealt by ranged weapons.
     * @return an integer
     */
    @Override
    public abstract int damage();

    /**
     * The hit chance of all ranged weapon
     * @return 100
     */
    @Override
    public int chanceToHit() {
        return 100;
    }

    /**
     * Each turn, it needs to recalculate the people it can shoot because they all moved.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        shootActions.clear();
        if (this.getAmmoAmount(actor) > 0) {
            shootActions.add(dfsAddEnemies(currentLocation, 0, new ArrayList<Location>()));
        }
    }

    @Override
    public List<Action> getAllowableActions() {
        return this.shootActions.getUnmodifiableActionList();
    }

    /**
     * Calculates the range between the target and the actor, and shoots the enemy if within the range
     * @param currentLocation the current location of the target
     * @param curDepth the range between the target and the actor
     * @param visited an ArrayList that contains all the locations checked within the range of the actor
     * @return ActionList that contains all the targets that can be shot
     */
    private ActionList dfsAddEnemies(Location currentLocation, int curDepth, ArrayList<Location> visited) {
        ActionList ret = new ActionList();
        // Base case
        if (curDepth == getRange()+1) {
            // Empty action list
            return new ActionList();
        }
        if (currentLocation.containsAnActor()) {
            Actor toBeAttacked = currentLocation.getActor();
            // Without this you can probably kill princess peach and toad.
            if (toBeAttacked.hasCapability(Status.HOSTILE_TO_PLAYER)) {
                ret.add(new RangedAttackAction(toBeAttacked, this));
            }
        }

        visited.add(currentLocation);
        for (Exit exit: currentLocation.getExits()) {
            Location destination = exit.getDestination();
            if (visited.contains(destination) || destination.getGround().hasCapability(GroundCharacteristics.BLOCKS_ARROWS)) {
                continue;
            }
            ret.add(dfsAddEnemies(destination, curDepth+1, visited));
        }
        return ret;
    }

    @Override
    public DropItemAction getDropAction(Actor actor){
        return null;
    }
}
