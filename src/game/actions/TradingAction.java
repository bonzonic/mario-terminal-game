package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.GameUtilities;
import game.Status;
import game.items.MagicPouch;
import game.items.Storable;
import game.items.Tradable;

/**
 * An action to be performed when the player wants to trade with toad
 */
public class TradingAction extends Action {

    /**
     * The item that is to be traded
     */
    private Tradable item;

    /**
     * Constructor
     * @param item -  a Tradable item
     */
    public TradingAction(Tradable item) {
        this.item = item;
    }

    /**
     * Executes the Trading Action
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a String either the player obtained it or the player don't have enough coins
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        MagicPouch magicPouch = (MagicPouch) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_STORABLES);
        double balance = magicPouch.getAmount(Storable.COIN);
        if (balance - item.getPrice() >= 0) {
            magicPouch.decreaseAmount(Storable.COIN, item.getPrice());
            actor.addItemToInventory(item.getItem());
            return actor + " obtained " + this.item.toString();
        }
        else {
            return "You don't have enough coins!";
        }
    }

    /**
     * The description of the player can choose to buy the item
     * @param actor The actor performing the action.
     * @return a String representing the option the player can choose to buy the item
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " buys " + this.item.toString() + " ($" + this.item.getPrice() + ")";
    }
}
