package game.managers;

import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.map.Maps;

import java.util.HashMap;

/**
 * Global singleton which manages the teleport points throughout the maps
 */
public class TeleportPointsManager {

    /**
     * HashMap that contains all the locations that can be teleported to
     */
    private final HashMap<Location, Location> locations;

    /**
     * An instance of the TeleportPointsManager class
     */
    private static TeleportPointsManager instance;

    /**
     * Private constructor for the TeleportPointsManager class
     */
    private TeleportPointsManager() {
        locations = new HashMap<>();
        addFixedLocations();
    }

    /**
     * Static factory method
     * @return an instance of this class
     */
    public static TeleportPointsManager getInstance(){
        if(instance == null){
            instance = new TeleportPointsManager();
        }
        return instance;
    }

    /**
     * Adds 2 locations to the hash map, one to go from and the other to teleport to
     * @param location - the starting location where you can teleport to somewhere else
     * @param newLocation - the destination location where you will end up
     */
    public void addLocation(Location location, Location newLocation) {
        locations.put(location, newLocation);
    }

    /**
     * Returns a location to teleport to
     * @param location - the starting location where you can teleport to somewhere else
     * @return a location where you will end up or null
     */
    public Location findLocationToTravel(Location location) {
        return locations.get(location);
    }

    /**
     * Adds fixed teleport points to the game
     */
    private void addFixedLocations() {
        // getting the maps
        MapManager mapManager = MapManager.getInstance();
        HashMap<Maps, GameMap> maps = mapManager.getMaps();

        // adding the teleport locations to the maps
        GameMap lavaMap = maps.get(Maps.MAP_LAVA);
        GameMap gameMap = maps.get(Maps.MAP_BASIC);
        locations.put(gameMap.at(12, 3), lavaMap.at(0,0));
        locations.put(gameMap.at(73, 1), lavaMap.at(0,0));
        locations.put(gameMap.at(10, 15), lavaMap.at(0, 0));
        locations.put(gameMap.at(46, 8),lavaMap.at(0, 0));
    }
}
