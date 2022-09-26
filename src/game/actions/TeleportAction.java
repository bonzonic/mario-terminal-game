package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.managers.MapManager;
import java.util.HashMap;

/**
 * An action which allows the player to teleport using warp pipes
 */
public class TeleportAction extends Action {

    /**
     * HashMap containing all the map names
     */
    private final HashMap<GameMap, String> mapNames;

    /**
     * The location to be teleported to
     */
    private final Location locationToTravel;

    /**
     * Constructor which takes in an argument for the location to teleport to
     * @param locationToTravel - a location to teleport to
     */
    public TeleportAction(Location locationToTravel) {
        MapManager mapManager = MapManager.getInstance();
        mapNames = mapManager.getMapNames(); // getting the names of the maps
        this.locationToTravel = locationToTravel;
    }

    /**
     * Teleports the actor to the new location
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return A string stating that Mario teleports to that location
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (locationToTravel.containsAnActor())
            map.removeActor(locationToTravel.getActor()); // removes the piranha plant if it is there
        map.moveActor(actor, locationToTravel);
        return "Mario teleports to " + mapNames.get(locationToTravel.map());
    }

    /**
     * A menu description whether the player would like to teleport to that map
     * @param actor The actor performing the action.
     * @return a string stating that mario could teleport to a certain map
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Teleport to " + mapNames.get(locationToTravel.map());
    }
}
