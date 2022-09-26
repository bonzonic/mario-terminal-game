package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Weapon.RangedWeapon;

/**
 * Ranged attack, a far distance attack to an enemy
 */
public class RangedAttackAction extends AttackAction{

    /**
     * The ranged weapon
     */
    private final RangedWeapon weapon;

    /**
     * Constructor.
     *
     * @param target    the Actor to attack
     * @param weapon Weapon of choice
     */
    public RangedAttackAction(Actor target, RangedWeapon weapon) {
        super(target, "a distance");
        this.weapon = weapon;
    }

    /**
     * Reduces the ammo by 1 and attacks the actor
     * @param actor - the actor holding the ranged weapon
     * @param map - the map the actor is at
     * @return a string states what happens to that enemy, killed etc
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        this.weapon.reduceAmmo(actor, 1);
        return super.attackActor(actor, this.weapon, map);
    }
}
