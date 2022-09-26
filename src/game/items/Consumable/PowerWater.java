package game.items.Consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.IntrinsicFighter;

/**
 * A liquid that powers up the actor that drinks it
 */
public class PowerWater extends Consumable {

    /**
     * The amount of attack boost gained by the actor
     */
    final int BASEATTACK_BOOST = 15;

    /***
     * Constructor.
     */
    public PowerWater() {
        super("Power Water", 'A', false);
    }

    @Override
    public String consume(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.CAN_INTRINSIC_ATTACK)) {
            IntrinsicFighter fighter = (IntrinsicFighter) actor;
            fighter.setIntrinsicDamage(fighter.getIntrinsicDamage() + BASEATTACK_BOOST);
        }
        return "";
    }
}
