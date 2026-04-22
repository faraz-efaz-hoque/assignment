package game;

import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

// NEW: inert collectible, weight 1.
public class FloppyDisk extends Item {

    public FloppyDisk() {
        super("Floppy Disk", '⊟');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(1));
        this.makePortable();
    }
}
