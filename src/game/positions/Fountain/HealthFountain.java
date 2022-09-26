package game.positions.Fountain;

import game.items.Consumable.HealingWater;

/**
 * A type of fountain that contains healing water
 */
public class HealthFountain extends Fountain {

    /**
     * Constructor.
     */
    public HealthFountain() {
        super('H', 10, new HealingWater());
    }

    @Override
    public String toString() {
        return "Health " + super.toString();
    }
}
