package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

/**
 * A class representing a small rectangular piece of plastic that holds entirely
 * too much power over your ability to walk through doors.
 * Its primary function is to beep happily when the player has clearance, and beep
 * angrily when they don't.
 * Essential for progressing the plot,
 *
 * @author Adrian Kristanto
 */
public class AccessCard extends Item {
    public AccessCard() {
        super("Access Card", '▤');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(1));
        this.makePortable();
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        Location location = map.locationOf(owner);
        for (Exit exit : location.getExits()) {
            Ground ground = exit.getDestination().getGround();
            if (ground instanceof Door door && !door.isUnlocked()) {
                actions.add(new UnlockDoorAction(door));
                break;
            }
        }
        return actions;
    }
}
