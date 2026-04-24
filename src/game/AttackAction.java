package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

// NEW: used by Undead via AttackBehaviour. Delegates hit/damage to the
// weapon's attack() method (which already handles the 10% hit roll).
// Handles death: calls target.unconscious(actor, map) if HP hits 0.
public class AttackAction extends Action {
    private final Actor target;
    private final String direction;

    public AttackAction(Actor target, String direction) {
        this.target = target;
        this.direction = direction;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        String result = actor.getIntrinsicWeapon().attack(actor, target, map);
        if (!target.isConscious()) {
            result += "\n" + target.unconscious(actor, map); // removes target from map
        }
        return result;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " attacks " + target + " at " + direction;
    }
}