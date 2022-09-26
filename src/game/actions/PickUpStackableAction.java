package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.GameMap;
import game.GameUtilities;
import game.Status;
import game.items.MagicPouch;
import game.items.Stackable;

/**
 * The action of picking up a coin
 */
public class PickUpStackableAction extends PickUpItemAction {
    private Stackable stack;
    private Item item;

    /**
     * Constructor
     */
    public PickUpStackableAction(Item item, Stackable stack) {
        super(item);
        this.item = item;
        this.stack = stack;
    }

    /**
     * Executes the action
     * @param actor The actor performing the action. -
     * @param map The map the actor is on.
     * @return a String
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = super.execute(actor, map);
        MagicPouch magicPouch = (MagicPouch) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_STORABLES);
        if (magicPouch != null) {
            magicPouch.increaseAmount(this.stack.getStorableType(), stack.getAmount());
            actor.removeItemFromInventory(this.item);
        }
        return ret;
    }
}
