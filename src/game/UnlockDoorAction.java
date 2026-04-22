package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;

/**
 * The bureaucratic process of asking a piece of the environment for permission to pass.
 */
public class UnlockDoorAction extends Action {

    private final Door door;

    public UnlockDoorAction(Door door) {
        this.door = door;
    }
    /**
     * When executed, it will search for a nearby door and unlock it.
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description of the result of the action of opening a door
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        door.unlock();
        return actor + " unlocks the Door.";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " unlocks Door with Access Card";
    }
}
