package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.positions.HigherGround;

import java.util.Random;

/**
 * Allows the player to jump to high grounds with a certain success rate
 */
public class JumpActorAction extends Action{

    /**
     * The high ground that the actor can jump onto
     */
    private HigherGround target;

    /**
     * The location of the high ground
     */
    private Location destination;

    /**
     * The direction of the high ground
     */
    private String direction;

    /**
     * Random Number Generator
     */
    private Random random;

    /**
     * A constructor for the JumpAction class
     * @param destination The place to jump to
     * @param direction The direction that actor is able to jump to
     * @param target who/what you want to jump on
     */
    public JumpActorAction(Location destination, String direction, HigherGround target) {
        this.direction = direction;
        this.destination = destination;
        this.target = target;
        this.random = new Random();
    }

    @Override
    public String execute(Actor actor, GameMap map){
        return this.target.jumpOn(actor, map, this.destination);
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " jumps to " + this.direction;
    }
}