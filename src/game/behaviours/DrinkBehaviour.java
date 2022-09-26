package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.ConsumeAction;
import game.positions.Fountain.Fountain;

import java.util.Random;

/**
 * A class that dictates how an actor drinks
 */
public class DrinkBehaviour implements Behaviour{

    /**
     * The probability that an actor drinks
     */
    final double DRINK_CHANCE = 0.8;

    /**
     * The amount of water slots that one sip/drink consumes.
     */
    private final int DRINK_CONSUME_AMOUNT = 2;

    /**
     * Random Number Generator
     */
    private Random random = new Random();

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location here = map.locationOf(actor);
        Ground ground = here.getGround();
        if (ground.hasCapability(Status.CAN_BE_DRUNK)) {
            // Can be drunk is only fountains
            Fountain fountain = (Fountain) ground;
            // Should it drink?
            if (random.nextDouble() <= DRINK_CHANCE) {
                fountain.reduceCapacity(this.DRINK_CONSUME_AMOUNT);
                return new ConsumeAction(fountain.getContents());
            }
        }
        return null;
    }
}
