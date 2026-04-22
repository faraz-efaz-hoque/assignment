package game;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

// NEW: offered by Puddle when direction is "" (actor standing on the tile).
// No puddle reference needed — the effect is entirely on the actor.
public class DrinkFromPuddleAction extends Action {

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasAbility(Abilities.STERILISING)) {
            actor.heal(1);
            return actor + " drinks purified puddle water. (+1 HP)";
        } else {
            actor.addStatus(new PoisonStatus(1, 3)); // 1 dmg/turn for 3 turns
            return actor + " drinks from the puddle... it's toxic! (1 damage/turn for 3 turns)";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " drinks from the Puddle";
    }
}
