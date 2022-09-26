package game.actions;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

/**
 * A class that ends the game
 */
public class EndGameAction extends MonologueAction {

    /**
     * The target that executes the end of the game
     */
    private Actor target;

    /**
     * A constructor for the EndGameAction class
     * @param target the target that executes the end of the game
     */
    public EndGameAction(Actor target){
        super(target);
        this.target = target;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String ret = "";
        ret += "Oh Mario, thank you for coming to save me!\n";
        ret += "You have completed the game!\n";
        ret += "Congratulations!\n";
        map.removeActor(actor);
        return ret;
    }
}
