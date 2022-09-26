package game.switchingAction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * Switch back to the normal menu!
 */
public class SwitchToNormalMenu extends SwitchingAction {

    /**
     * Getter for the name of this menu
     * @return "Normal" string
     */
    @Override
    protected String getName() {
        return "Normal";
    }

    /**
     * No action to be performed
     * @param menu - the Menu object to take in the option the player chosed
     * @param actor - actor
     * @param actionList - can be used to add more actions in it
     * @param display - to display the options
     * @return null
     */
    @Override
    protected Action getNextAction(Menu menu, Actor actor, ActionList actionList, Display display) {
        return null;
    }

    /**
     * Removes the capability of new menu
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @see SwitchingAction execute method
     * @return the String from the SwitchingAction execute method
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String result = super.execute(actor, map);
        actor.removeCapability(Status.NEW_MENU); // no new menu, return to old
        return result;
    }
}
