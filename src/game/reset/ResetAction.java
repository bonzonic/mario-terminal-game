package game.reset;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.reset.ResetManager;

/**
 * An action which resets the entire application
 */
public class ResetAction extends Action {

    /**
     * An instance of the ResetManager class
     */
    private ResetManager resetManager;

    /**
     * Constructor which creates a ResetManager instance
     */
    public ResetAction() {
        resetManager = ResetManager.getInstance();
    }

    /**
     * Executes the reset operation
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return an empty string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        resetManager.run();
        return "";
    }

    /**
     * The menu description to reset the game
     * @param actor The actor performing the action.
     * @return a string as an option to reset the game
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Reset the game";
    }

    /**
     * Returns the hotkey of "r"
     * @return a string representing the hotkey "r"
     */
    @Override
    public String hotkey() {
        return "r";
    }
}
