package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.ActorStatistics;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.statistics.StatisticOperations;

// NEW: decreases max HP (toxic) or heals 1 HP (sterilised).
// Removes the packet from inventory after the last cookie.
public class ConsumeCookiesAction extends Action {
    private final Cookies cookies;

    public ConsumeCookiesAction(Cookies cookies) {
        this.cookies = cookies;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = cookies.consumedBy(actor, map); // MODIFIED: was cookies.consume() + inline if/else
        if (cookies.isExhausted()) {
            actor.getInventory().remove(cookies);        // unchanged
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats Cookie (" + cookies.getRemainingUses() + " remaining)";
    }
}
