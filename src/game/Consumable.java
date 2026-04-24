package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;

// NEW: shared interface for items edible by both workers and creatures.
// consumedBy() holds the effect logic; removal from inventory/ground is
// handled by the calling action (ConsumeXAction for workers, EatAction for slimes).
public interface Consumable {
    String consumedBy(Actor actor, GameMap map);
    boolean isExhausted();
}