package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.AttackAction;

import java.util.Random;

/**
 * A class that dictates how an enemy attacks the Player
 */
public class AttackBehaviour implements Behaviour {

    /**
     * The Actor that is to be attacked
     */
    private Actor target;

    /**
     * Random object used to generate probabilities
     */
    private Random random = new Random();

    /**
     * Constructor
     * @param target The actor that is to be attacked
     */
    public AttackBehaviour(Actor target){
        this.target = target;
    }

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Location there = map.locationOf(target);

        for (Exit exit : here.getExits()){
            Location destination = exit.getDestination();
            if (destination == there && !target.hasCapability(Status.INVULNERABLE)){
                return new AttackAction(target, exit.getName());
            }
        }
        return null;
    }
}
