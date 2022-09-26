package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actions.UnlockChestAction;

/**
 * A shiny wooden box that drops goodies!
 */
public class TreasureChest extends Ground {

    /**
     * Constructor for the TreasureChest class
     */
    public TreasureChest(){
        super('X');
    }

    @Override
    public boolean canActorEnter(Actor actor){
        return false;
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction){
        ActionList ret = new ActionList();
        ret.add(new UnlockChestAction(location, direction));
        return ret;
    }
}
