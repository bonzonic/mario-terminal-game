package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.GameUtilities;
import game.Status;
import game.items.*;
import game.items.Weapon.RangedWeapon;
import game.reset.ResetAction;
import game.reset.Resettable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Class representing the Player.
 */
public class Player extends Actor implements Resettable, IntrinsicFighter {

  /**
   * The display menu
   */
  private final Menu menu = new Menu();

  /**
   * The reset trigger
   */
  private boolean resetTimes = true;

  /**
   * The magic pouch that contains all the materials held by the Player
   */
  private MagicPouch magicPouch = new MagicPouch();

  private int attackDamage = 5;

  /**
   * Constructor.
   *
   * @param name        Name to call the player in the UI
   * @param displayChar Character to represent the player in the UI
   * @param hitPoints   Player's starting number of hitpoints
   */
  public Player(String name, char displayChar, int hitPoints) {
    super(name, displayChar, hitPoints);
    addingCapabilities();
    addingItems();
    registerInstance();
  }

  /**
   * Adds capabilities to the Player
   */
  private void addingCapabilities() {
    this.addCapability(Status.HOSTILE_TO_ENEMY);
    this.addCapability(Status.CAN_JUMP);
    this.addCapability(Status.WALKABLE_FOR_PLAYER);
    this.addCapability(Status.CAN_TELEPORT);
    this.addCapability(Status.CAN_INTRINSIC_ATTACK);
  }

  /**
   * Adds items to the Player's inventory
   */
  private void addingItems() {
    magicPouch.setAmount(Storable.COIN, 1000);
    this.addItemToInventory(magicPouch);
  }

  /**
   * The player's actions to play
   * @param actions    collection of possible Actions for this Actor
   * @param lastAction The Action this Actor took last turn. Can do interesting
   *     things in conjunction with Action.getNextAction()
   * @param map        the map containing the Actor
   * @param display    the I/O object to which messages may be written
   * @return an Action to be played
   */
  @Override
  public Action playTurn(ActionList actions, Action lastAction, GameMap map,
                         Display display) {
    Location playerPos = map.locationOf(this);
    display.println("Mario" + this.printHp() + " at "
            + "(" + playerPos.x() + ", " + playerPos.y() + ")");
    display.println("Wallet: $" + this.magicPouch.getAmount(Storable.COIN));
    display.println("Wood: " + this.magicPouch.getAmount(Storable.WOOD));
    display.println("Arrow: " + this.magicPouch.getAmount(Storable.ARROW));
    // return/print the console menu
    if (this.hasCapability(Status.POWER_STAR)) {
      display.println("Mario is INVINCIBLE!");
    }
    // Handle multi-turn Actions
    if (this.hasCapability(Status.NEW_MENU) || lastAction.getNextAction() != null) {
      this.removeCapability(Status.NEW_MENU);
      return lastAction.getNextAction();
    }
    if (resetTimes) {
      actions.add(new ResetAction());
    }
    return menu.showMenu(this, actions, display);
  }

  /**
   * Returns a character whether it be 'm' or 'M' if player have a status of
   * TALL
   * @return a character either 'm' or 'M'
   */
  @Override
  public char getDisplayChar() {
    return this.hasCapability(Status.TALL)
        ? Character.toUpperCase(super.getDisplayChar())
        : super.getDisplayChar();
  }
  /**
   * Resets the statuses on the player and heals it to maximum health
   */
  @Override
  public void resetInstance() {
    resetTimes = false;
    heal(getMaxHp());
    this.removeCapability(Status.POWER_STAR);
    this.removeCapability(Status.TALL);
  }

  @Override
  public void setIntrinsicDamage(int damage) {
    this.attackDamage = damage;
  }

  @Override
  public int getIntrinsicDamage() {
    return this.attackDamage;
  }

  @Override
  public IntrinsicWeapon getIntrinsicWeapon() {
    return new IntrinsicWeapon(this.attackDamage, "punches");
  }

  @Override
  public Weapon getWeapon() {
    // chooses weapon with highest damage
    List<Weapon> weapons = new ArrayList<>();
    // Don't forget the base weapon as well.
    weapons.add(this.getIntrinsicWeapon());
    for (Item item : this.getInventory()) {
      if (item.asWeapon() != null) {
        weapons.add((Weapon)item);
      }
    }
    Collections.sort(weapons, new SortByDamage());
    return weapons.get(0);
  }

  /**
   * A sorting class that sorts the list of weapons by their damage in
   * descending order.
   */
  public class SortByDamage implements Comparator<Weapon> {

    public int compare(Weapon x, Weapon y) { return y.damage() - x.damage(); }
  }
}
