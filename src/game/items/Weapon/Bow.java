package game.items.Weapon;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.GameUtilities;
import game.Status;
import game.items.Craftable;
import game.items.MagicPouch;
import game.items.Storable;

import java.util.HashMap;

/**
 * A weapon used to fire arrows, useless without arrows.
 */
public class Bow extends RangedWeapon implements Craftable {

    // Assume infinite arrows for now...
    // ArrayList<Arrow> arrows;

    /**
     * ActionList containing all the actions for Bow
     */
    // Unfortunately the one in Item isn't public.
    protected ActionList bowActions = new ActionList();

    /**
     * The range of the Bow
     */
    private final int BOW_RANGE = 5;

    /**
     * Constructor.
     */
    public Bow() {
        super("Bow", '>', true);
    }

    /**
     * Returns the bow range
     * @return an integer
     */
    @Override
    public int getRange() {
        return BOW_RANGE;
    }

    /**
     * Gets the amount of arrows the actor has
     * @param actor - the actor
     * @return either 0 or the number of arrows the actor has
     */
    @Override
    public int getAmmoAmount(Actor actor) {
        MagicPouch magicPouch = (MagicPouch) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_STORABLES);
        if (magicPouch == null) {
            return 0;
        }
        return magicPouch.getAmount(Storable.ARROW);
    }

    /**
     * Reduces the arrows once it was used
     * @param actor - the actor shooting the arrow
     * @param amount - the amount of arrows shot
     */
    @Override
    public void reduceAmmo(Actor actor, int amount) {
        MagicPouch magicPouch = (MagicPouch) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_STORABLES);
        if (magicPouch != null) {
            magicPouch.decreaseAmount(Storable.ARROW, 1);
        }
    }

    /**
     * Each turn, it needs to recalculate the people it can shoot because they all moved.
     * @param currentLocation The location of the actor carrying this Item.
     * @param actor The actor carrying this Item.
     */
    @Override
    public void tick(Location currentLocation, Actor actor) {
        // Change condition to "has at least one arrow" in the future.
        if (true) {
            super.tick(currentLocation, actor);
        }
        // this.addAction(new AttackAction(, this));
    }

    /**
     * The damage dealt by Bow (requires arrows)
     * @return 40
     */
    @Override
    public int damage() {
        return 40;
    }

    /**
     * The sound effect of Bow
     * @return shoots
     */
    @Override
    public String verb() {
        return "shoots";
    }

    /**
     * Returns the recipe for crafting Bow
     * @return the recipe for crafting Bow
     */
    @Override
    public HashMap<Storable, Integer> getRecipe(){
        HashMap<Storable, Integer> ret = new HashMap<Storable, Integer>();
        ret.put(Storable.WOOD, 5);
        return ret;
    }

    /**
     * Getter for Bow
     * @return Bow
     */
    @Override
    public Item getCrafted(){
        return this;
    }
}
