package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

// NEW: checks STERILISING ability on the actor (returns true if they carry a
// SterilisationBox). Removes the apple from inventory on use.
public class ConsumeAppleAction extends Action {
    private final Apple apple;

    public ConsumeAppleAction(Apple apple) {
        this.apple = apple;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        actor.getInventory().remove(apple); // disappears immediately on use
        if (actor.hasAbility(Abilities.STERILISING)) {
            actor.heal(3);
            return actor + " eats a sterilised apple. (+3 HP)";
        } else {
            actor.addStatus(new PoisonStatus(1, 5)); // 1 dmg/turn for 5 turns
            return actor + " eats a spoiled apple... it's toxic! (1 damage/turn for 5 turns)";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats Apple";
    }
}
