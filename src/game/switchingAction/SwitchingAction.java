package game.switchingAction;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * An action which provides a new menu with options for player to choose from
 */
public abstract class SwitchingAction extends Action {

    private final Menu menu = new Menu();
    private final Display display = new Display();
    private final ActionList actionList = new ActionList();
    private Actor actor;

    /**
     * Allows the player to switch to a different menu
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Switching to ___ mode string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.actor = actor;
        actor.addCapability(Status.NEW_MENU);
        actionList.add(new SwitchToNormalMenu());
        return "Switching to " + getName() + " Mode";
    }

    /**
     * @param actor The actor performing the action.
     * @return a string stating the switch to that menu
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Switch to " + getName() + " Menu";
    }

    /**
     * The name of the mode
     * @return - a String representing the name of the mode
     */
    protected abstract String getName();

    /**
     * To allow the player to choose which action it would like to perform
     * @param menu - the Menu object to take in the option the player chosed
     * @param actor - actor
     * @param actionList - can be used to add more actions in it
     * @param display - to display the options
     * @return an Action to be performed
     */
    protected abstract Action getNextAction(Menu menu, Actor actor, ActionList actionList, Display display);

    /**
     * Returns an action to the player to be executed
     * @return an action
     */
    @Override
    public Action getNextAction() {
        return getNextAction(menu, actor, actionList, display);
    }
}
