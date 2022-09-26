package game.positions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import game.Status;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

	/**
	 * Constructor for the Floor class
	 */
	public Floor() {
		super('_');
	}

	@Override
	public boolean canActorEnter(Actor actor) {
		// Only those who can walk on the floor can enter the floor.
		return actor.hasCapability(Status.WALKABLE_FOR_PLAYER);
	}
}
