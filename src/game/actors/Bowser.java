package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.Behaviour;
import game.behaviours.FollowBehaviour;
import game.reset.Resettable;

import java.util.HashMap;
import java.util.Map;

/**
 * A gigantic evil Koopa that stole your princess
 */
public class Bowser extends Actor implements Resettable {

    /**
     * The HashMap that contains all the behaviours of Bowser
     */
    private final Map<Integer, Behaviour> behaviours = new HashMap<>(); // priority, behaviour

    /**
     * A constructor for the Bowser class
     */
    public Bowser() {
        super("Bowser", 'B', 500);
        this.addCapability(Status.HOSTILE_TO_PLAYER);
        this.addCapability(Status.FIRE_BREATHER);
        registerInstance();
    }

    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actions.add(new AttackAction(this,direction));
        }
        if (otherActor instanceof Player) {
            this.behaviours.put(8, new FollowBehaviour(otherActor));
            this.behaviours.put(9, new AttackBehaviour(otherActor));
        }
        return actions;
    }

    /**
     * Figure out what to do next.
     * @see Actor#playTurn(ActionList, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {
        Location spot = map.at(9,12);
        if (this.hasCapability(Status.RESETTABLE) && !spot.containsAnActor()) {
            map.moveActor(this, spot);
            this.resetMaxHp(500);
            this.behaviours.remove(8);
            this.removeCapability(Status.RESETTABLE);
        }
        else {
            for (Behaviour Behaviour : behaviours.values()) {
                Action action = Behaviour.getAction(this, map);
                if (action != null)
                    return action;
            }
        }
        return new DoNothingAction();
    }

    /**
     * Adds the RESETTABLE capability to this instance
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }

    @Override
    protected IntrinsicWeapon getIntrinsicWeapon(){
        return new IntrinsicWeapon(80, "punches");
    }
}
