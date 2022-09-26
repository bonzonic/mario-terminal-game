package game.items.Consumable;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

/**
 * A liquid that heals the actor that drinks it
 */
public class HealingWater extends Consumable{

    /**
     * The amount of health points healed by the actor
     */
    final int HEAL_AMOUNT = 50;

    /***
     * Constructor.
     */
    public HealingWater() {
        super("Healing Water", 'H', true);
    }

    @Override
    public String consume(Actor actor, GameMap map) {
        actor.heal(HEAL_AMOUNT);
        return null;
    }
}
