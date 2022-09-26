package game.switchingAction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import game.injectors.TradableInjector;

/**
 * Switch to shop menu!
 */
public class SwitchToShopMenu extends SwitchingAction{
    /**
     * getter for the name of this menu
     * @return a String called "Shop"
     */
    @Override
    protected String getName() {
        return "Shop";
    }

    /**
     * Adds all the tradable actions in it
     * @param menu - the Menu object to take in the option the player chosed
     * @param actor - actor
     * @param actionList - can be used to add more actions in it
     * @param display - to display the options
     * @return an action to be executed, chosen by the player
     */
    @Override
    protected Action getNextAction(Menu menu, Actor actor, ActionList actionList, Display display) {
        TradableInjector injector = new TradableInjector();

        // creating all the tradable items in an injector to reduce dependencies on toad
        ActionList tradableItems = injector.addingTradableItems();
        for (Action action: tradableItems) {
            actionList.add(action);
        }
        return menu.showMenu(actor, actionList, display);
    }
}
