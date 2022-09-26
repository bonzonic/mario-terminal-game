package game.switchingAction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import game.injectors.CraftableInjector;

/**
 * Switch to Crafting menu!
 */
public class SwitchToCraftingMenu extends SwitchingAction {

    /**
     * Asks the player to choose what crafting related action it would like to perform
     * @param menu - the Menu object to take in the option the player chose
     * @param actor - actor
     * @param actionList - can be used to add more actions in it
     * @param display - to display the options
     * @return an action to be executed
     */
    @Override
    protected Action getNextAction(Menu menu, Actor actor, ActionList actionList, Display display) {
        CraftableInjector craftableInjector = new CraftableInjector();
        ActionList craftableItems = craftableInjector.addingCraftableItems();
        for (Action action: craftableItems) {
            actionList.add(action);
        }
        return menu.showMenu(actor, actionList,display);
    }

    /**
     * Getter for the name for this menu
     * @return a string called "Crafting"
     */
    @Override
    protected String getName() {
        return "Crafting";
    }
}
