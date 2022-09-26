package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.GameUtilities;
import game.Status;
import game.items.Bottle;

import java.util.ArrayList;
import java.util.Random;

import static game.Status.*;

/**
 * An action where the Actor is talking to the palyer.
 */
public class MonologueAction extends Action {

    /**
     * The hotkey used for the action
     */
    private String hotkey;

    /**
     * The actor performing the action
     */
    private Actor actor;

    private final Random random;

    /**
     * Constructor
     * @param targetActor The target actor
     */
    public MonologueAction(Actor targetActor) {
        this.actor = targetActor;
        random = new Random();
    }

    /**
     * Executes the MonologueAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String indicating that the player has talked with the other actor.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        ArrayList<String> dialoguePossibilities = new ArrayList<String>();
        boolean hasBottle = GameUtilities.getItemWithCapability(actor, CAN_CARRY_LIQUIDS) != null;
        boolean hasWrench = false;
        boolean hasPowerStar = actor.hasCapability(POWER_STAR);

        if (!hasBottle) {
            actor.addItemToInventory(new Bottle());
            return this.actor + ": \"" + "It's dangerous to go alone! Take this Bottle." + "\"";
        }

        for (Item i: actor.getInventory()) {
            if (i.toString().equalsIgnoreCase("Wrench")) {
                hasWrench = true;
                break;
            }
        }
        dialoguePossibilities.add("The Princess is depending on you! You are our only hope.");
        dialoguePossibilities.add("Being imprisoned in these walls can drive a fungus crazy :(");
        if (!hasWrench) {
            dialoguePossibilities.add("You might need a wrench to smash Koopa's hard shells.");
        }
        if (!hasPowerStar) {
            dialoguePossibilities.add("You better get back to finding the Power Stars.");
        }
        return this.actor + ": \"" + dialoguePossibilities.get(random.nextInt(dialoguePossibilities.size())) + "\"";
    }

    /**
     * A description once the player has talked with the monologue-er.
     * @param actor The actor performing the action.
     * @return a string that indicates the player hastalked with the other actor.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " talks with " + this.actor;
    }
}
