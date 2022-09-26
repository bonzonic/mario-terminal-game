package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.GameUtilities;
import game.Status;
import game.items.Craftable;
import game.items.MagicPouch;
import game.items.Storable;

/**
 * Special Action for crafting items at the Crafting Table.
 */
public class CraftAction extends Action {

    /**
     * Item to be crafted
     */
    private final Craftable item;

    /**
     * Constructor
     * @param item the item to be crafted
     */
    public CraftAction(Craftable item){
        this.item = item;
    }

    /**
     * Crafts the item if there is enough materials to craft it
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string stating either the actor crafted the item or the actor does not have enough material to craft it
     */
    @Override
    public String execute(Actor actor, GameMap map){
        MagicPouch magicPouch = (MagicPouch) GameUtilities.getItemWithCapability(actor, Status.CAN_CARRY_STORABLES);

        boolean sufficientMaterials = true;
        for (Storable storable: this.item.getRecipe().keySet()) {
            if (magicPouch.getAmount(storable) < this.item.getRecipe().getOrDefault(storable, 0)) {
                sufficientMaterials = false;
                break;
            }
        }

        if (sufficientMaterials) {
            // Reduce the materials.
            for (Storable storable: this.item.getRecipe().keySet()) {
                magicPouch.decreaseAmount(storable, this.item.getRecipe().get(storable));
            }

            // Give it to the actor
            actor.addItemToInventory(this.item.getCrafted());
            return actor + " crafted " + this.item;
        }
        return actor + " does not have enough materials to craft " + item;
    }

    /**
     * A string representing the character crafting the item
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " crafts " + item + " (" + item.getRecipe() + ") ";
    }
}
