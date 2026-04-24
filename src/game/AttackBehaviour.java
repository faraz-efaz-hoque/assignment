package game;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

// NEW: used by Undead (priority 1). Scans all 8 adjacent tiles.
// Uses hasAbility(Abilities.WORKER) to identify worker targets —
// this ignores other Undeads/Slimes automatically since they don't
// have the WORKER ability.
public class AttackBehaviour implements Behaviour<Actor, AttackAction> {

    @Override
    public AttackAction operate(Actor actor, Location location) {
        for (Exit exit : location.getExits()) {
            Location destination = exit.getDestination();
            if (destination.containsAnActor()) {
                Actor target = destination.getActor();
                if (target.hasAbility(Abilities.WORKER)) {
                    return new AttackAction(target, exit.getName());
                }
            }
        }
        return null;
    }
}