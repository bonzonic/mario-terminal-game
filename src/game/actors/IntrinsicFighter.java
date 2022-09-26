package game.actors;

/**
 * Actors that can do an intrinsic attack, and whose base attack damage from intrinsic fighting
 * can increase/decrease.
 */
public interface IntrinsicFighter {

    /**
     * Setter for intrinsic damage
     * @param damage the damage dealt by the intrinsic attack
     */
    void setIntrinsicDamage(int damage);

    /**
     * Getter for intrinsic damage
     * @return the damage dealt by the intrinsic attack
     */
    int getIntrinsicDamage();
}
