package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

/**
 * A burning ground
 */
public class Lava extends Ground {

    /**
     * Constructor
     */
    public Lava() {
        super('L');
    }

    /**
     * Only player can enter the lava
     * @param actor the Actor to check
     * @return true or false
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.hasCapability(Status.WALKABLE_FOR_PLAYER);
    }

    /**
     * Player gets damaged for 15 health if it stays there
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        Actor player;
        if (location.containsAnActor()) {
            player = location.getActor();
            player.hurt(15);

            // if lesser than 0 hp then gg.
            if (!player.isConscious())
                location.map().removeActor(player);
        }
    }
}
