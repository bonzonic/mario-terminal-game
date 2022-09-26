package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Status;
import game.actors.Player;
import game.items.Wood;
import game.positions.Dirt;
import game.positions.Tree.Tree;

/**
 * Special Action for chopping Trees.
 */
public class ChopAction extends Action {

    /**
     * The tree that is to be chopped
     */
    private Tree target;

    /**
     * The direction of the tree
     */
    private String direction;

    /**
     * The location of the tree
     */
    private Location location;

    private int woodAmount;
    /**
     * A constructor for the ChopAction class
     * @param target the tree that is to be chopped
     * @param direction the direction of the tree
     * @param location the location of the tree
     */
    public ChopAction(Tree target, String direction, Location location, int woodAmount){
        this.target = target;
        this.direction = direction;
        this.location = location;
        this.woodAmount = woodAmount;
    }

    /**
     * Selects the axe if the player has it, if not, the player punches the tree
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string in which the actor chops or punches the type of tree
     */
    @Override
    public String execute(Actor actor, GameMap map){
        Weapon weapon = ((Player) actor).getIntrinsicWeapon();
        for (Item item : actor.getInventory()){
            if (item.hasCapability(Status.CAN_CHOP)){
                weapon = item.asWeapon();
            }
        }
        target.chop(weapon.damage());
        if (target.getHp() <= 0){
            this.location.setGround(new Dirt());
            this.location.addItem(new Wood(woodAmount));
            return target + " falls";
        }
        return actor + " " + weapon.verb() + " " + target;
    }

    /**
     * The menu description which the player can select it
     * @param actor The actor performing the action.
     * @return a string stating if the player wishes to chop the tree at that direction
     */
    @Override
    public String menuDescription(Actor actor){
        return actor + " chops " +  target + " at " + direction;
    }
}
