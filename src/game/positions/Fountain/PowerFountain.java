package game.positions.Fountain;

import game.items.Consumable.PowerWater;

/**
 * A type of fountain that contains power water
 */
public class PowerFountain extends Fountain {

    /**
     * Constructor.
     */
    public PowerFountain() {
        super('A', 10, new PowerWater());
    }

    @Override
    public String toString() {
       return "Power " + super.toString();
    }
}
