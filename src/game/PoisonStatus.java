package game;

import edu.monash.fit2099.engine.GameEntity;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.capabilities.Status;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Location;

// NEW: a damage-over-time status. GameMap.tick() calls actor.tickStatuses()
// each turn, which calls tickStatus() on every attached Status, then removes
// it when isStatusActive() returns false.
public class PoisonStatus implements Status {
    private final int damagePerTurn;
    private int turnsRemaining;

    public PoisonStatus(int damagePerTurn, int turnsRemaining) {
        this.damagePerTurn = damagePerTurn;
        this.turnsRemaining = turnsRemaining;
    }

    @Override
    public void tickStatus(GameEntity entity, Location location) {
        if (entity instanceof Actor actor) { // Java 21 pattern matching cast
            actor.hurt(damagePerTurn);
            new Display().println(actor + " is poisoned! (-" + damagePerTurn
                    + " HP, " + (turnsRemaining - 1) + " turns remaining)");
        }
        turnsRemaining--;
    }

    @Override
    public boolean isStatusActive() {
        return turnsRemaining > 0; // engine removes this status when false
    }
}
