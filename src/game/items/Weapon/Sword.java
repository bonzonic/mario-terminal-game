package game.items.Weapon;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.items.Craftable;
import game.items.Storable;

import java.util.HashMap;

/**
 * A sword that slashes enemies
 */
public class Sword extends Item implements Weapon, Craftable {

    /**
     * A constructor for the Sword class
     */
    public Sword(){
        super("Sword", 's', true);
    }

    /**
     * The damage dealt with a Sword
     * @return 80
     */
    @Override
    public int damage() {
        return 80;
    }

    /**
     * The chance to hit with a Sword
     * @return 80
     */
    @Override
    public int chanceToHit(){
        return 80;
    }

    /**
     * The sound effect the Sword creates
     * @return slashes
     */
    @Override
    public String verb(){
        return "slashes";
    }

    /**
     * Returns the recipe for crafting Sword
     * @return the recipe for crafting Sword
     */
    @Override
    public HashMap<Storable, Integer> getRecipe(){
        HashMap<Storable, Integer> ret = new HashMap<Storable, Integer>();
        ret.put(Storable.WOOD, 3);
        return ret;
    }

    /**
     * Getter for Sword
     * @return Sword
     */
    @Override
    public Item getCrafted(){
        return this;
    }
}
