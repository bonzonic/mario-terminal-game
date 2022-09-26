package game.positions;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actions.TeleportAction;
import game.actors.PiranhaPlant;
import game.managers.TeleportPointsManager;
import game.reset.Resettable;

/**
 * Green pipes that mario can use to teleport to other locations
 */
public class WarpPipe extends HigherGround implements Resettable {

    /**
     * The age of the warp pipe
     */
    private int age = 0;

    /**
     * Constructor
     */
    public WarpPipe() {
        super('C');
        registerInstance();
    }

    /**
     * Mario gets hurt for 10 damage if he fails to jump
     * @return an integer, 10
     */
    @Override
    public int getFallDamageRate() {
        return 10;
    }

    /**
     * Chances to fall is 10% when jumping to warp pipe
     * @return 0.9 - success chance
     */
    @Override
    public double getFallProb() {
        return 0.9;
    }

    /**
     * Adds the teleport action as a possible action for the player if they would like to teleport to the other map
     *
     * @param actor the Actor acting
     * @param location the current Location
     * @param direction the direction of the Ground from the Actor
     * @return a new, empty collection of Actions
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList lst = super.allowableActions(actor, location, direction);

        // checking if the actor is on the pipe and can teleport
        if (direction.isEmpty() && actor.hasCapability(Status.CAN_TELEPORT)) {
            TeleportPointsManager teleportPointsManager = TeleportPointsManager.getInstance();
            Location locationToTravel = teleportPointsManager.findLocationToTravel(location);

            // it may be a broken pipe
            if (locationToTravel != null) {
                lst.add(new TeleportAction(locationToTravel));
                teleportPointsManager.addLocation(locationToTravel, location); // remembers the location from the pipe
            }
        }
        return lst;
    }

    /**
     * After one turn, piranha plant will spawn on the warp pipe
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);
        age++;
        if (this.hasCapability(Status.RESETTABLE)) {
            this.age = 0;
            this.removeCapability(Status.RESETTABLE);
        }
        if (age == 1 && !location.containsAnActor())
            location.addActor(new PiranhaPlant());
    }

    /**
     * "Warp Pipe" - the name of this object
     * @return "Warp Pipe"
     */
    @Override
    public String toString() {
        return "Warp Pipe";
    }

    /**
     * Adds the RESETTABLE capability to this instance
     */
    @Override
    public void resetInstance() {
        this.addCapability(Status.RESETTABLE);
    }
}
