package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

// NEW: inert collectible, weight 30 — nearly fills the whole 50-unit inventory.
public class CRTMonitor extends Item {

    public CRTMonitor() {
        super("CRT Monitor", '◙');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(30));
        this.makePortable();
    }
}
