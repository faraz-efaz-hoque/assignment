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
        String result = apple.consumedBy(actor, map); // MODIFIED: was inline if/else; now delegates to Apple
        actor.getInventory().remove(apple);            // unchanged — worker removes from inventory
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " eats Apple";
    }
}
