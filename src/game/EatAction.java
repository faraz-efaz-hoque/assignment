package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;

// NEW: Slime's consume action — item stays on ground (no inventory).
// consumedBy() applies the same effect a worker would get.
// If exhausted, removes item from ground (cast to Item is safe since
// all Consumable implementations extend Item).
public class EatAction extends Action {
    private final Consumable consumable;

    public EatAction(Consumable consumable) {
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = consumable.consumedBy(actor, map); // applies effect to slime
        if (consumable.isExhausted()) {
            map.locationOf(actor).removeItem((Item) consumable); // remove from ground
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats " + consumable;
    }
}