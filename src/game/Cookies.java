package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

// NEW: 5-use consumable. Unlike Apple it stays in inventory until all 5 are
// eaten — the action removes it only when isExhausted() is true.
public class Cookies extends Item {
    private int usesRemaining = 5;

    public Cookies() {
        super("Cookies", '◍');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(2));
        this.makePortable();
    }

    public int getRemainingUses() { return usesRemaining; }
    public void consume() { usesRemaining--; }
    public boolean isExhausted() { return usesRemaining == 0; }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        if (usesRemaining > 0) {
            actions.add(new ConsumeCookiesAction(this));
        }
        return actions;
    }
}
