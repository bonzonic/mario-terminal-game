package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Goomba;
import game.items.*;
import game.items.Consumable.PowerStar;
import game.items.Consumable.SuperMushroom;
import game.items.Weapon.Arrow;
import game.items.Weapon.GalaxySword;
import game.items.Weapon.Gun;
import game.positions.Dirt;

import java.util.Random;

/**
 * Special Action for unlocking Treasure Chests
 */
public class UnlockChestAction extends Action {

    /**
     * Random Number Generator
     */
    private Random random;

    /**
     * The location of the Treasure Chest
     */
    private Location location;

    /**
     * The direction of the Treasure Chest
     */
    private String direction;

    /**
     * Constructor
     */
    public UnlockChestAction(Location location, String direction){
        this.random = new Random();
        this.location = location;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map){
        String ret = "";
        if (random.nextDouble() <= 0.15){
            location.addActor(new Goomba());
            ret += "It was a trap box! A Goomba appears.";
        }
        else{
            int arrowDrop = random.nextInt(3)+1;
            int coinDrop = random.nextInt(101)+20;
            actor.addItemToInventory(new Arrow(arrowDrop));
            actor.addItemToInventory(new Coin(coinDrop));
            ret += "Treasure Chest dropped " + arrowDrop + " arrows\n";
            ret += "Treasure Chest dropped " + coinDrop + " coins\n";
            double rng = random.nextDouble();
            if (rng <= 0.05){
                actor.addItemToInventory(new Gun());
                ret += "Treasure Chest dropped a gun, what?";
            }
            else if (rng <= 0.10){
                actor.addItemToInventory(new GalaxySword());
                ret += "Treasure Chest dropped a purple sword";
            }
            else if (rng <= 0.30){
                actor.addItemToInventory(new PowerStar());
                ret += "Treasure Chest dropped a Power Star";
            }
            else if (rng <= 0.60){
                actor.addItemToInventory(new Wrench());
                ret += "Treasure Chest dropped a Wrench";
            }
            else{
                actor.addItemToInventory(new SuperMushroom());
                ret += "Treasure Chest dropped a Super Mushroom";
            }
        }
        location.setGround(new Dirt());
        return ret;
    }

    @Override
    public String menuDescription(Actor actor){
        return actor + " unlocks Treasure Chest at " + direction;
    }
}
