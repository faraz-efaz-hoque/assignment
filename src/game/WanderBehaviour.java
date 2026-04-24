package game;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.behaviours.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Location;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// NEW: shared by Undead and Slime as their fallback behaviour.
// Shuffles the exit list so movement is random, not directional.
// canActorEnter() handles walls and locked doors automatically.
public class WanderBehaviour implements Behaviour<Actor, MoveActorAction> {

    @Override
    public MoveActorAction operate(Actor actor, Location location) {
        List<Exit> exits = new ArrayList<>(location.getExits()); // copy — list is unmodifiable
        Collections.shuffle(exits);
        for (Exit exit : exits) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                return new MoveActorAction(destination, exit.getName(), exit.getHotKey());
            }
        }
        return null; // boxed in, do nothing
    }
}