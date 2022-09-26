package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable.Consumable;

/**
 * An action where the player can consume the item
 */
public class ConsumeAction extends Action {

    /**
     * Item that is to be consumed
     */
    private Consumable consumable;

    /**
     * Constructor that accepts the consumable item
     * @param consumable - a consumable item
     */
    public ConsumeAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     * Executes the consumeAction
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String indicating that the player has consumed the item
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String additionalInfo = this.consumable.consume(actor, map);
        additionalInfo = (additionalInfo == null || additionalInfo.isEmpty()) ? "" : " - " + additionalInfo;
        return actor.toString() + " consumes " + consumable.toString() + additionalInfo;
    }

    /**
     * A description once the player has consumed the item
     * @param actor The actor performing the action.
     * @return a string that indicates the player has consumed the item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " consumes " + consumable.toString();
    }
}
