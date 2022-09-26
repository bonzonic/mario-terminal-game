package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.GameUtilities;
import game.Status;
import game.items.Bottle;
import game.items.Consumable.Consumable;
import game.positions.Fountain.Fountain;

/**
 * Special Action that fills up empty bottles
 */
public class FillBottleAction extends Action {

    /**
     * The fountain that contains liquid
     */
    private Fountain fountain;

    /**
     * The amount of water slots filling up a bottle consumes.
     */
    private final int FILL_BOTTLE_AMOUNT = 1;

    /**
     * Constructor for the FillBottleAction class
     * @param fountain the fountain that contains liquid
     */
    public FillBottleAction(Fountain fountain) {
        this.fountain = fountain;
    }

    /**
     * Executes the filling bottle action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String indicating that the player has filled the bottle the item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Bottle bottle = (Bottle) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_LIQUIDS);
        if (bottle == null) {
            return actor.toString() + " doesn't have a water bottle!";
        }
        Consumable fillee = this.fountain.getContents();
        bottle.addConsumable(fillee);
        this.fountain.reduceCapacity(this.FILL_BOTTLE_AMOUNT); // Reduce the water from the fountain.

        return actor.toString() + " has filled their bottle with " + fillee.toString();
    }

    /**
     * A description once the player has filled the item
     * @param actor The actor performing the action.
     * @return a string that indicates the player has filled the item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " fills their bottle from " + this.fountain.toString();
    }
}
