package game.positions;

import edu.monash.fit2099.engine.positions.Ground;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

	/**
	 * Dirt constructor
	 */
	public Dirt() {
		super('.');
		// Dirt is fertile
		this.addCapability(GroundCharacteristics.FERTILE);
	}
}
