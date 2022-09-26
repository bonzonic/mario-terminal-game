package game.items.Weapon;

/**
 * A super overpowered sword that slashes through dimensions.
 */
public class GalaxySword extends Sword{

    /**
     * Galaxy sword's character '!'
     */
    public GalaxySword(){
        this.setDisplayChar('!');
    }

    /**
     * The op damage of galaxy sword
     * @return an integer - 400
     */
    @Override
    public int damage(){
        return 400;
    }

    /**
     * @return the name of this item, Galaxy Sword
     */
    @Override
    public String toString(){
        return "Galaxy Sword";
    }
}
