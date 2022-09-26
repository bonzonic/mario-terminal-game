package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.items.PickUpItemAction;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A fireball that Bowser spits out when attacking, sort of like a furball, don't you think?
 */
public class Fire extends Item{

    /**
     * The age of the fireball
     */
    private int age = 0;

    /**
     * The number of turns it takes for the fire to disappear
     */
    private final int EXPIRY_TURNS = 3;

    /**
     * Constructor for the Fire class
     */
    public Fire() {
        super("Fire", 'v', true);
    }

    @Override
    public void tick(Location currentLocation) {
        Actor actor;
        age++;
        if (currentLocation.containsAnActor()){
            actor = currentLocation.getActor();
            actor.hurt(20);

            if (!actor.isConscious()) {
                if (actor.hasCapability(Status.FIRE_BREATHER)){
                    currentLocation.addItem(new Key());
                }
                currentLocation.map().removeActor(actor);
            }
        }
        if (age >= EXPIRY_TURNS) {
            currentLocation.removeItem(this);
        }
    }

    @Override
    public PickUpItemAction getPickUpAction(Actor actor) {
        return null;
    }
}
