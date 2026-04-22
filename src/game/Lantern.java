package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

// NEW: passive item. tick(Location, Actor) is called by GameMap.tick() each
// turn while the item is in an actor's inventory.
public class Lantern extends Item {
    private int oilFuel = 10;

    public Lantern() {
        super("Lantern", '&');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(7));
        this.makePortable();
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (oilFuel > 0 && Math.random() < 0.05) { // 5% chance per turn
            oilFuel--;
            // replace the current tile with fire; FireGround stores the old
            // ground so it can restore it after 5 turns
            currentLocation.setGround(new FireGround(currentLocation.getGround()));
        }
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        return new ActionList(); // no manual use — purely passive
    }
}
