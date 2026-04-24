package game;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.BaseStatistic;

// NEW: single-use consumable. Declares its own action via allowableActions —
// no instanceof checks anywhere else.
public class Apple extends Item implements Consumable {

    public Apple() {
        super("Apple", 'ó');
        this.addNewStatistic(ItemStatistics.WEIGHT, new BaseStatistic(1));
        this.makePortable();
    }
    // MODIFIED: added — contains the effect logic previously inside ConsumeAppleAction
    @Override
    public String consumedBy(Actor actor, GameMap map) {
        if (actor.hasAbility(Abilities.STERILISING)) {
            actor.heal(3);
            return actor + " eats a sterilised apple. (+3 HP)";
        } else {
            actor.addStatus(new PoisonStatus(1, 5));
            return actor + " eats a spoiled apple... it's toxic! (1 damage/turn for 5 turns)";
        }
    }

    // MODIFIED: added — Apple is always single-use
    @Override
    public boolean isExhausted() { return true; }

    @Override
    public ActionList allowableActions(Actor owner, GameMap map) {
        ActionList actions = new ActionList();
        actions.add(new ConsumeAppleAction(this));
        return actions;
    }
}
