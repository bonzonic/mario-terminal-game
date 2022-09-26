package game.actions;

import java.util.Random;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.items.Weapon.Axe;
import game.items.Fire;
import game.items.Key;
import game.items.Consumable.SuperMushroom;

/**
 * Special Action for attacking other Actors.
 */
public class AttackAction extends Action {

	/**
	 * The Actor that is to be attacked
	 */
	protected Actor target;

	/**
	 * The direction of incoming attack.
	 */
	protected String direction;

	/**
	 * Random number generator
	 */
	protected Random rand = new Random();

	/**
	 * Constructor.
	 * 
	 * @param target the Actor to attack
	 */
	public AttackAction(Actor target, String direction) {
		this.target = target;
		this.direction = direction;
	}

	/**
	 * Allows actors to attack other actors.
	 * @param actor the actor that is attacking
	 * @param weapon the weapon used by the actor
	 * @param map the game map
	 * @return the result after the attack
	 */
	protected String attackActor(Actor actor, Weapon weapon, GameMap map) {
		String result = "";

		if (!(rand.nextInt(100) <= weapon.chanceToHit())) {
			result = actor + " misses " + target + ".";
		}
		else {
			int damage = weapon.damage();
			boolean hasPowerStar = actor.hasCapability(Status.POWER_STAR);
			Location location = map.locationOf(target);

			result = actor + " " + weapon.verb() + " " + target + " for " + damage + " damage.";
			target.hurt(damage);
			if (actor.hasCapability(Status.FIRE_BREATHER)){
				location.addItem(new Fire());
			}

			if(target.hasCapability(Status.TALL)){
				target.removeCapability(Status.TALL);
			}
			if (!target.isConscious() || actor.hasCapability(Status.POWER_STAR)) {
				if (target.toString().contains("Koopa")) {
					if (target.getDisplayChar() == 'D' && weapon.toString().equalsIgnoreCase("Wrench")){
						map.removeActor(target);
						location.addItem(new SuperMushroom());
						return "Shell breaks";
					}
					return target + " becomes a shell.";
				}
				if (target.hasCapability(Status.FIRE_BREATHER)){
					location.addItem(new Key());
				}
				if (target.toString().equalsIgnoreCase("Goomba")){
					map.removeActor(target);
					if (!(rand.nextInt(100) <= 20)){
						location.addItem(new Axe());
					}
				}
				ActionList dropActions = new ActionList();
				// drop all items
				for (Item item : target.getInventory())
					dropActions.add(item.getDropAction(actor));
				for (Action drop : dropActions)
					drop.execute(target, map);
				// remove actor
				map.removeActor(target);
				result += System.lineSeparator() + target + " is killed.";
			}
		}
		return result;
	}

	@Override
	public String execute(Actor actor, GameMap map) {
		Weapon weapon = actor.getWeapon();
		return attackActor(actor, weapon, map);
	}

	@Override
	public String menuDescription(Actor actor) {
		if(target.getDisplayChar() == 'D'){
			return actor + " breaks shell";
		}
		return actor + " attacks " + target + " at " + direction;
	}
}
