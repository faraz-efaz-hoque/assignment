package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

public class SterilisationBox extends Item {

    public SterilisationBox() {
        super("Sterilisation Box", '▣');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(7));
        this.makePortable();
        this.enableAbility(Abilities.STERILISING); // MODIFIED: gives the item
        // the STERILISING ability. Actor.hasAbility() checks all inventory
        // items, so any actor carrying this box will return true for
        // hasAbility(Abilities.STERILISING) without any extra code.
    }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new UseSterilisationBoxAction(this));
        return actions;
    }
}
