package game.injectors;

import edu.monash.fit2099.engine.actions.ActionList;
import game.actions.CraftAction;
import game.items.Weapon.Bow;
import game.items.Weapon.Sword;

/**
 * Injector for craftable items, instantiates all the required craftable items and actions to be given to the Crafting Table.
 */
public class CraftableInjector {

    /**
     * Returns a list containing CraftAction for every craftable item
     * @return a list containing CraftAction for every craftable item
     */
    public ActionList addingCraftableItems() {
        ActionList lst = new ActionList();
        lst.add(new CraftAction(new Sword()));
        lst.add(new CraftAction(new Bow()));
        return lst;
    }
}
