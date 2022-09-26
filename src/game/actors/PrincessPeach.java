package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.EndGameAction;

/**
 * The princess that has been captured by Bowser for the 105th time
 */
public class PrincessPeach extends Actor{

    /**
     * A constructor for the PrincessPeach class
     */
    public PrincessPeach() {
        super("Peach", 'P', 100);
        this.addCapability(Status.INVULNERABLE);
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList ret = new ActionList();
        if (otherActor.hasCapability(Status.HAS_KEY)) {
            Action talk = new EndGameAction(this);
            ret.add(talk);
        }
        return ret;
    }
}
