package game;

import java.util.HashMap;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.Bowser;
import game.actors.Player;
import game.actors.PrincessPeach;
import game.actors.Toad;
import game.items.Weapon.Bow;
import game.managers.MapManager;
import game.items.Consumable.PowerStar;
import game.items.Consumable.SuperMushroom;
import game.map.Maps;

/**
 * The main class for the Mario World game.
 *
 */
public class Application {

	public static void main(String[] args) {

		World world = new World(new Display());

		// process of adding maps into the world
		MapManager mapManager = MapManager.getInstance();
		HashMap<Maps, GameMap> maps = mapManager.getMaps();

		for (GameMap map: maps.values()) {
			world.addGameMap(map);
		}

		// We can choose which gamemap to start from
		GameMap gameMap = maps.get(Maps.MAP_BASIC); // basic zone is the application's starting point
		final int MARIO_POS_X = 44;
		final int MARIO_POS_Y = 10;
		Actor mario = new Player("Player", 'm', 100);
		world.addPlayer(mario, gameMap.at(MARIO_POS_X, MARIO_POS_Y));
		gameMap.at(MARIO_POS_X, MARIO_POS_Y).addItem(new PowerStar());
		gameMap.at(MARIO_POS_X, MARIO_POS_Y).addItem(new SuperMushroom());
		gameMap.at(MARIO_POS_X+2, MARIO_POS_Y+2).addItem(new Bow());

		gameMap.at(MARIO_POS_X, MARIO_POS_Y+1).addActor(new Toad());

		GameMap lavaMap = maps.get(Maps.MAP_LAVA);
		lavaMap.at(9,13).addActor(new PrincessPeach());
		lavaMap.at(9,12).addActor(new Bowser());
		lavaMap.at(MARIO_POS_X, MARIO_POS_Y+1).addActor(new Toad());
		world.run();

	}
}
