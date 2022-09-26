package game.injectors;

import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import game.map.Maps;
import game.positions.*;
import game.positions.Fountain.HealthFountain;
import game.positions.Fountain.PowerFountain;
import game.positions.Tree.SproutTree;
import java.io.IOException;
import java.util.HashMap;

/**
 * Generates all the maps and puts them to a hash map for easy usage for other classes
 */
public class MapInjector {

    /**
     * The HashMap that contains all the maps in the game
     */
    private final HashMap<Maps, GameMap> maps;

    /**
     * The HashMap that contains all the map names in the game
     */
    private final HashMap<GameMap, String> mapNames;

    /**
     * Constructor
     */
    public MapInjector() {
        maps = new HashMap<>();
        mapNames = new HashMap<>();
    }

    /**
     * Creating the maps and putting them to a hash map where classes can access it using the Maps enum
     * @return maps - a hashmap of <Maps, GameMap>
     */
    public HashMap<Maps, GameMap> addingMaps() {
        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new SproutTree(), new WarpPipe(), new HealthFountain(), new PowerFountain(), new CraftingTable(), new TreasureChest());
        FancyGroundFactory lavaFactory = new FancyGroundFactory(new Dirt(), new Wall(), new Floor(), new SproutTree(), new Lava(), new WarpPipe(), new HealthFountain(), new PowerFountain(), new CraftingTable(), new TreasureChest());
        GameMap lavaMap = null;
        GameMap gameMap = null;
        try {
            lavaMap = new GameMap(lavaFactory, "src/game/map/Lava Zone");
            gameMap = new GameMap(groundFactory, "src/game/map/Basic Map");
        } catch (IOException e) {
            e.printStackTrace();
        }
        maps.put(Maps.MAP_LAVA, lavaMap);
        maps.put(Maps.MAP_BASIC, gameMap);
        return maps;
    }

    /**
     * Creates another hashmap to put all the map names in it. Map names are obtainable by passing in the map
     * @return mapNames - a hashmap of <GameMap, String> which other classes can know what is the map name easily
     */
    public HashMap<GameMap, String> addingMapNames() {
        GameMap lavaMap = maps.get(Maps.MAP_LAVA);
        GameMap gameMap = maps.get(Maps.MAP_BASIC);
        mapNames.put(gameMap, "Basic Map");
        mapNames.put(lavaMap, "Lava Zone");
        return mapNames;
    }


}
