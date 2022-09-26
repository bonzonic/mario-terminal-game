package game.positions;

/**
 * An enumeration of statuses for the ground.
 */
public enum GroundCharacteristics {
    FERTILE,// use this status to indicate if a ground is fertile and a tree can grow on it.
    JUMPABLE, // Use this to indicate if a ground is able to be jumped upon.
    BLOCKS_ARROWS, // Arrows (and other ranged weapon ammo) can't go through here.
}
