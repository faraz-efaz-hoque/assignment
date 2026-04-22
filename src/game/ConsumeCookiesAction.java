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
        cookies.consume();
        String result;
        if (actor.hasAbility(Abilities.STERILISING)) {
            actor.heal(1);
            result = actor + " eats a sterilised cookie. (+1 HP, " + cookies.getRemainingUses() + " left)";
        } else {
            // permanently lowers max HP; BaseStatistic also caps current HP to new max
            actor.modifyStatisticMaximum(ActorStatistics.HEALTH, StatisticOperations.DECREASE, 1);
            result = actor + " eats a cookie. (Max HP -1, " + cookies.getRemainingUses() + " left)";
        }
        if (cookies.isExhausted()) {
            actor.getInventory().remove(cookies); // all 5 eaten — remove from inventory
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats Cookie (" + cookies.getRemainingUses() + " remaining)";
    }
}
