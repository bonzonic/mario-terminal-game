package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.ConsumeAction;
import game.items.Consumable.Consumable;

import java.util.Stack;

/**
 * A glass bottle that holds liquid
 */
public class Bottle extends Consumable {

    /**
     * A stack that stores all the bottles
     */
    private Stack<Consumable> liquids = new Stack<Consumable>();

    /**
     * The action used to consume the liquid in the bottle
     */
    private ConsumeAction consumeAction;

    /***
     * Constructor.
     */
    public Bottle() {
        super("Bottle", 'B', false);
        this.addCapability(Status.CAN_CARRY_LIQUIDS);
        this.consumeAction = new ConsumeAction(this);
        this.addAction(consumeAction);
    }

    /**
     * Adds a bottle that contains liquid into the stack
     * @param consumable the bottle that contains liquid
     */
    public void addConsumable(Consumable consumable) {
        liquids.push(consumable);
    }

    @Override
    public String toString() {
        return (super.toString() + liquids);
    }

    @Override
    public String consume(Actor actor, GameMap map) {
        if (liquids.size() > 0) {
            liquids.pop().consume(actor, map);
            return "";
        }
        return "But Alas! It is empty!";
    }
}
