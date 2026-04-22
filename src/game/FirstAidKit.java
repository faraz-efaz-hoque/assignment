package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

public class FirstAidKit extends Item {
    private int cooldown = 0;

    public FirstAidKit() {
        super("First Aid Kit", '+');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(25));
        this.makePortable();
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        if (cooldown > 0) {
            cooldown--;  // only ticks while carried; on-ground tick() is not overridden so it does nothing
        }
    }

    public void startCooldown() {
        cooldown = 20;
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        if (cooldown == 0) {
            actions.add(new UseFirstAidKitAction(this));
        }
        return actions;
    }
}
