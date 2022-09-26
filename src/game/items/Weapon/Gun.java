package game.items.Weapon;

import edu.monash.fit2099.engine.actors.Actor;

/**
 * A Glock, why is this in the game?
 */
public class Gun extends RangedWeapon{

    public Gun(){
        super("Gun", 'G',true);
    }

    @Override
    public int getRange() {
        return Integer.MAX_VALUE;
    }

    /**
     * Returns death
     * @return death
     */
    @Override
    public int damage(){
        return 9999;
    }

    /**
     * Gets the amount of arrows the actor has
     * @param actor - the actor
     * @return either 0 or the number of arrows the actor has
     */
    public int getAmmoAmount(Actor actor) {
        // Infinite by default
        return 1;
    }

    /**
     * Returns what a Gun does (verb)
     * @return what a gun does (verb)
     */
    @Override
    public String verb(){
        return "deletes";
    }

    @Override
    public void reduceAmmo(Actor actor, int amount) {
    }
}
