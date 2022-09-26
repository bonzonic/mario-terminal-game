package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.DropItemAction;
import edu.monash.fit2099.engine.items.Item;
import game.Status;

/**
 * A key that unlocks Princess Peach's handcuffs.
 */
public class Key extends Item {

    /**
     * A constructor for the Key class.
     */
    public Key(){
        super("Key", 'k', true);
    }

    @Override
    public DropItemAction getDropAction(Actor actor) {
        actor.addCapability(Status.HAS_KEY);
        return new DropItemAction(this);
    }
}
