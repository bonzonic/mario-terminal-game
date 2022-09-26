package game.actors;

import edu.monash.fit2099.engine.actions.Action;
        import edu.monash.fit2099.engine.actions.ActionList;
        import edu.monash.fit2099.engine.actions.DoNothingAction;
        import edu.monash.fit2099.engine.actors.Actor;
        import edu.monash.fit2099.engine.displays.Display;
        import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.MonologueAction;
import game.injectors.TradableInjector;
import game.switchingAction.SwitchToShopMenu;

/**
 * A mushroom head Actor named Toad
 */
public class Toad extends Actor {

    /**
     * Constructor
     */
    public Toad() {
        super("Toad", 'O', 10);
    }

    /**
     * Toad does nothing every round, he just stands there providing trades with others
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return a DoNothingAction
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        return new DoNothingAction();
    }

    /**
     * Adds Monologue to it and Trading Actions which contain the tradable items
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return ActionList - a list of actions to be performed by Toad such as the monologue and trading
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList ret = new ActionList();
        Action talk = new MonologueAction(this);
        ret.add(talk);
        ret.add(new SwitchToShopMenu());
        return ret;
    }
}
